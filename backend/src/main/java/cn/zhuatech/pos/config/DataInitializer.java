/* Copyright 2026 Shanghai Rujing Zhihua Information Technology Co., Ltd. */
package cn.zhuatech.pos.config;
import cn.zhuatech.pos.model.*;
import cn.zhuatech.pos.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.time.LocalDateTime;
@Component
public class DataInitializer implements CommandLineRunner {
    private final UserRepository users; private final ProductRepository products; private final StoreRepository stores;
    private final MemberRepository members; private final PosOrderRepository orders; private final PosOrderItemRepository items;
    private final CashierShiftRepository shifts; private final PasswordEncoder encoder;
    public DataInitializer(UserRepository users, ProductRepository products, StoreRepository stores, MemberRepository members,
                           PosOrderRepository orders, PosOrderItemRepository items, CashierShiftRepository shifts, PasswordEncoder encoder) {
        this.users = users; this.products = products; this.stores = stores; this.members = members;
        this.orders = orders; this.items = items; this.shifts = shifts; this.encoder = encoder;
    }
    @Override public void run(String... args) {
        if (users.count() > 0) return;
        users.save(new UserAccount("admin", encoder.encode("ZhuaTech@2026"), "系统管理员", UserAccount.Role.ADMIN, "全部门店"));
        users.save(new UserAccount("manager", encoder.encode("Demo@2026"), "林店长", UserAccount.Role.MANAGER, "上海静安旗舰店"));
        users.save(new UserAccount("cashier", encoder.encode("Demo@2026"), "周雨晴", UserAccount.Role.CASHIER, "上海静安旗舰店"));
        users.save(new UserAccount("auditor", encoder.encode("Demo@2026"), "财务审计员", UserAccount.Role.AUDITOR, "全部门店"));

        products.save(new Product("FD-10021", "6901002100018", "海盐厚乳拿铁", "现制饮品", new BigDecimal("22.00"), new BigDecimal("19.80"), 48, Product.Status.ACTIVE, "sage"));
        products.save(new Product("FD-10034", "6901003400018", "青提茉莉冰茶", "现制饮品", new BigDecimal("18.00"), new BigDecimal("16.20"), 36, Product.Status.ACTIVE, "mint"));
        products.save(new Product("BK-20018", "6902001800018", "黄油可颂", "烘焙轻食", new BigDecimal("12.00"), new BigDecimal("10.80"), 22, Product.Status.ACTIVE, "sand"));
        products.save(new Product("BK-20043", "6902004300018", "烟熏鸡肉恰巴塔", "烘焙轻食", new BigDecimal("26.00"), new BigDecimal("23.40"), 14, Product.Status.ACTIVE, "amber"));
        products.save(new Product("FR-30012", "6903001200018", "草莓希腊酸奶杯", "鲜食乳品", new BigDecimal("16.80"), new BigDecimal("15.00"), 18, Product.Status.ACTIVE, "rose"));
        products.save(new Product("FR-30027", "6903002700018", "冷萃咖啡 280ml", "鲜食乳品", new BigDecimal("15.00"), new BigDecimal("13.50"), 8, Product.Status.LOW_STOCK, "slate"));
        products.save(new Product("SN-40008", "6904000800018", "海苔脆片", "休闲零食", new BigDecimal("9.90"), new BigDecimal("8.90"), 63, Product.Status.ACTIVE, "olive"));
        products.save(new Product("SN-40031", "6904003100018", "每日坚果 25g", "休闲零食", new BigDecimal("8.00"), new BigDecimal("7.20"), 52, Product.Status.ACTIVE, "cocoa"));
        products.save(new Product("LF-50006", "6905000600018", "便携湿巾 10 抽", "生活用品", new BigDecimal("5.00"), new BigDecimal("4.50"), 45, Product.Status.ACTIVE, "blue"));

        stores.save(new Store("SH-JA-01", "上海静安旗舰店", "上海", "南京西路 1188 号", Store.Status.OPEN, 4, new BigDecimal("28640.50"), 382));
        stores.save(new Store("SH-XH-02", "上海徐汇滨江店", "上海", "龙腾大道 2266 号", Store.Status.OPEN, 3, new BigDecimal("22180.00"), 296));
        stores.save(new Store("HZ-HB-01", "杭州湖滨店", "杭州", "延安路 258 号", Store.Status.OPEN, 3, new BigDecimal("19872.60"), 268));
        stores.save(new Store("SZ-GY-03", "苏州工业园区店", "苏州", "苏州大道东 268 号", Store.Status.OPEN, 2, new BigDecimal("14890.20"), 201));

        members.save(new Member("M202603180021", "沈女士", "138****2716", "黑金会员", 2860, new BigDecimal("328.00"), new BigDecimal("12680.50")));
        members.save(new Member("M202605090116", "陈先生", "139****8832", "金卡会员", 1680, new BigDecimal("86.50"), new BigDecimal("8240.00")));
        members.save(new Member("M202607110328", "陆女士", "136****5210", "银卡会员", 620, BigDecimal.ZERO, new BigDecimal("2380.60")));

        LocalDateTime now = LocalDateTime.now();
        shifts.save(new CashierShift("SHIFT-20260728-02", "上海静安旗舰店", "POS-02", "周雨晴", CashierShift.Status.OPEN,
            now.minusHours(4), new BigDecimal("1000.00"), new BigDecimal("3386.00"), 74));
        saveOrder("POS202607281458021", "沈女士", PosOrder.PaymentMethod.WECHAT, new BigDecimal("52.80"), new BigDecimal("4.80"), 3, "海盐厚乳拿铁");
        saveOrder("POS202607281454116", null, PosOrder.PaymentMethod.ALIPAY, new BigDecimal("27.90"), BigDecimal.ZERO, 2, "黄油可颂");
        saveOrder("POS202607281448392", "陈先生", PosOrder.PaymentMethod.BANK_CARD, new BigDecimal("68.00"), new BigDecimal("6.80"), 4, "烟熏鸡肉恰巴塔");
        saveOrder("POS202607281441508", null, PosOrder.PaymentMethod.CASH, new BigDecimal("15.00"), BigDecimal.ZERO, 1, "冷萃咖啡 280ml");
    }
    private void saveOrder(String orderNo, String memberName, PosOrder.PaymentMethod payment, BigDecimal subtotal,
                           BigDecimal discount, int count, String productName) {
        BigDecimal payable = subtotal.subtract(discount);
        orders.save(new PosOrder(orderNo, "上海静安旗舰店", "POS-02", "周雨晴", memberName,
            payment, PosOrder.Status.PAID, subtotal, discount, payable, payable, count));
        items.save(new PosOrderItem(orderNo, "DEMO-SKU", productName, subtotal.divide(BigDecimal.valueOf(count), 2, java.math.RoundingMode.HALF_UP), count, subtotal));
    }
}
