/* Copyright 2026 Shanghai Rujing Zhihua Information Technology Co., Ltd. */
package cn.zhuatech.pos.service;

import cn.zhuatech.pos.common.BusinessException;
import cn.zhuatech.pos.dto.PosDto.*;
import cn.zhuatech.pos.model.*;
import cn.zhuatech.pos.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class PosService {
    private final ProductRepository products;
    private final PosOrderRepository orders;
    private final PosOrderItemRepository orderItems;
    private final StoreRepository stores;
    private final MemberRepository members;
    private final CurrentUserService currentUser;
    public PosService(ProductRepository products, PosOrderRepository orders, PosOrderItemRepository orderItems,
                      StoreRepository stores, MemberRepository members, CurrentUserService currentUser) {
        this.products = products; this.orders = orders; this.orderItems = orderItems;
        this.stores = stores; this.members = members; this.currentUser = currentUser;
    }

    @Transactional(readOnly = true)
    public DashboardView dashboard() {
        BigDecimal sales = stores.findAll().stream().map(Store::getTodaySales).reduce(BigDecimal.ZERO, BigDecimal::add);
        int orderCount = stores.findAll().stream().mapToInt(Store::getTodayOrders).sum();
        BigDecimal ticket = orderCount == 0 ? BigDecimal.ZERO : sales.divide(BigDecimal.valueOf(orderCount), 2, RoundingMode.HALF_UP);
        DashboardSummary summary = new DashboardSummary(sales, orderCount, ticket,
            (int) stores.findAll().stream().filter(store -> store.getStatus() == Store.Status.OPEN).count(),
            stores.findAll().stream().mapToInt(Store::getRegisterCount).sum(),
            orders.countByStatus(PosOrder.Status.REFUNDED), 86);
        List<HourlySales> hourly = List.of(
            new HourlySales("09:00", new BigDecimal("6840"), 94), new HourlySales("11:00", new BigDecimal("12860"), 168),
            new HourlySales("13:00", new BigDecimal("10320"), 139), new HourlySales("15:00", new BigDecimal("14980"), 182),
            new HourlySales("17:00", new BigDecimal("18420"), 221), new HourlySales("19:00", new BigDecimal("16280"), 196));
        List<CategorySales> categories = List.of(
            new CategorySales("即食饮品", new BigDecimal("26480"), 31), new CategorySales("烘焙轻食", new BigDecimal("20560"), 24),
            new CategorySales("鲜食乳品", new BigDecimal("17120"), 20), new CategorySales("休闲零食", new BigDecimal("12840"), 15),
            new CategorySales("生活用品", new BigDecimal("8560"), 10));
        List<OperationAlert> alerts = List.of(
            new OperationAlert("HIGH", "南京西路店 03 号机离线", "连续 8 分钟未收到心跳，请检查网络与收银服务", "运维值班"),
            new OperationAlert("MEDIUM", "冷萃咖啡库存低于安全线", "全渠道可售 8 瓶，预计 16:40 售罄", "商品运营"),
            new OperationAlert("LOW", "2 笔退款等待复核", "单笔金额超过门店授权额度", "区域经理"));
        return new DashboardView(summary, hourly, categories, alerts);
    }

    @Transactional
    public CheckoutResult checkout(CheckoutRequest request) {
        PosOrder.PaymentMethod payment;
        try { payment = PosOrder.PaymentMethod.valueOf(request.paymentMethod()); }
        catch (IllegalArgumentException exception) { throw new BusinessException("支付方式不正确"); }
        List<CheckoutLineResult> lines = new ArrayList<>();
        BigDecimal subtotal = BigDecimal.ZERO;
        int itemCount = 0;
        for (CheckoutLine line : request.items()) {
            Product product = products.findBySku(line.sku()).orElseThrow(() -> new BusinessException("商品不存在：" + line.sku()));
            try { product.reduceStock(line.quantity()); }
            catch (IllegalArgumentException exception) { throw new BusinessException(product.getName() + "库存不足"); }
            BigDecimal amount = product.getPrice().multiply(BigDecimal.valueOf(line.quantity()));
            subtotal = subtotal.add(amount); itemCount += line.quantity();
            lines.add(new CheckoutLineResult(product.getSku(), product.getName(), product.getPrice(), line.quantity(), amount));
        }
        BigDecimal discount = request.discount() == null ? BigDecimal.ZERO : request.discount();
        if (discount.compareTo(subtotal) > 0) throw new BusinessException("优惠金额不能超过商品金额");
        BigDecimal payable = subtotal.subtract(discount);
        String memberName = request.memberMobile() == null || request.memberMobile().isBlank() ? null :
            members.findByMobile(request.memberMobile()).map(Member::getName).orElseThrow(() -> new BusinessException("未找到会员"));
        String orderNo = "POS" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS"));
        PosOrder order = orders.save(new PosOrder(orderNo, request.storeName(), request.registerNo(),
            currentUser.get().getFullName(), memberName, payment, PosOrder.Status.PAID,
            subtotal, discount, payable, payable, itemCount));
        lines.forEach(line -> orderItems.save(new PosOrderItem(orderNo, line.sku(), line.productName(), line.unitPrice(), line.quantity(), line.lineAmount())));
        return new CheckoutResult(order.getOrderNo(), subtotal, discount, payable, payment.name(), memberName, lines);
    }
}
