/* Copyright 2026 Shanghai Rujing Zhihua Information Technology Co., Ltd. */
package cn.zhuatech.pos.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "pos_order")
public class PosOrder extends BaseEntity {
    public enum Status { PAID, REFUNDED, PARTIAL_REFUND, VOIDED }
    public enum PaymentMethod { WECHAT, ALIPAY, CASH, BANK_CARD, MIXED }
    @Column(nullable = false, unique = true, length = 32) private String orderNo;
    @Column(nullable = false, length = 80) private String storeName;
    @Column(nullable = false, length = 24) private String registerNo;
    @Column(nullable = false, length = 40) private String cashierName;
    @Column(length = 50) private String memberName;
    @Enumerated(EnumType.STRING) @Column(nullable = false, length = 20) private PaymentMethod paymentMethod;
    @Enumerated(EnumType.STRING) @Column(nullable = false, length = 20) private Status status;
    @Column(nullable = false, precision = 14, scale = 2) private BigDecimal subtotal;
    @Column(nullable = false, precision = 14, scale = 2) private BigDecimal discount;
    @Column(nullable = false, precision = 14, scale = 2) private BigDecimal payable;
    @Column(nullable = false, precision = 14, scale = 2) private BigDecimal paid;
    @Column(nullable = false) private int itemCount;
    protected PosOrder() {}
    public PosOrder(String orderNo, String storeName, String registerNo, String cashierName, String memberName,
                    PaymentMethod paymentMethod, Status status, BigDecimal subtotal, BigDecimal discount,
                    BigDecimal payable, BigDecimal paid, int itemCount) {
        this.orderNo = orderNo; this.storeName = storeName; this.registerNo = registerNo;
        this.cashierName = cashierName; this.memberName = memberName; this.paymentMethod = paymentMethod;
        this.status = status; this.subtotal = subtotal; this.discount = discount;
        this.payable = payable; this.paid = paid; this.itemCount = itemCount;
    }
    public String getOrderNo() { return orderNo; }
    public String getStoreName() { return storeName; }
    public String getRegisterNo() { return registerNo; }
    public String getCashierName() { return cashierName; }
    public String getMemberName() { return memberName; }
    public PaymentMethod getPaymentMethod() { return paymentMethod; }
    public Status getStatus() { return status; }
    public BigDecimal getSubtotal() { return subtotal; }
    public BigDecimal getDiscount() { return discount; }
    public BigDecimal getPayable() { return payable; }
    public BigDecimal getPaid() { return paid; }
    public int getItemCount() { return itemCount; }
}
