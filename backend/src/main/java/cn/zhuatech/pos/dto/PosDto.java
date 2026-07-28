/* Copyright 2026 Shanghai Rujing Zhihua Information Technology Co., Ltd. */
package cn.zhuatech.pos.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.util.List;

public final class PosDto {
    private PosDto() {}
    public record DashboardSummary(BigDecimal todaySales, int todayOrders, BigDecimal averageTicket,
                                   int activeStores, int activeRegisters, long refundOrders, int newMembers) {}
    public record HourlySales(String hour, BigDecimal sales, int orders) {}
    public record CategorySales(String category, BigDecimal amount, int ratio) {}
    public record OperationAlert(String level, String title, String detail, String owner) {}
    public record DashboardView(DashboardSummary summary, List<HourlySales> hourly,
                                List<CategorySales> categories, List<OperationAlert> alerts) {}
    public record CheckoutLine(@NotBlank(message = "商品 SKU 不能为空") String sku,
                               @Min(value = 1, message = "商品数量至少为 1") int quantity) {}
    public record CheckoutRequest(@NotBlank(message = "请选择门店") String storeName,
                                  @NotBlank(message = "收银机号不能为空") String registerNo,
                                  String memberMobile,
                                  @NotBlank(message = "请选择支付方式") String paymentMethod,
                                  @NotEmpty(message = "购物车不能为空") List<@Valid CheckoutLine> items,
                                  @DecimalMin(value = "0.00", message = "优惠金额不能小于 0") BigDecimal discount) {}
    public record CheckoutLineResult(String sku, String productName, BigDecimal unitPrice, int quantity, BigDecimal lineAmount) {}
    public record CheckoutResult(String orderNo, BigDecimal subtotal, BigDecimal discount, BigDecimal payable,
                                 String paymentMethod, String memberName, List<CheckoutLineResult> items) {}
}
