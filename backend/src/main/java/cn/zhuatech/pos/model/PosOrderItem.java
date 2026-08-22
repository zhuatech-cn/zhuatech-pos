/* Copyright 2026 Shanghai Rujing Zhihua Information Technology Co., Ltd. · https://www.zhuatech.cn/ */
package cn.zhuatech.pos.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "pos_order_item")
public class PosOrderItem extends BaseEntity {
    @Column(nullable = false, length = 32) private String orderNo;
    @Column(nullable = false, length = 40) private String sku;
    @Column(nullable = false, length = 120) private String productName;
    @Column(nullable = false, precision = 12, scale = 2) private BigDecimal unitPrice;
    @Column(nullable = false) private int quantity;
    @Column(nullable = false, precision = 14, scale = 2) private BigDecimal lineAmount;
    protected PosOrderItem() {}
    public PosOrderItem(String orderNo, String sku, String productName, BigDecimal unitPrice, int quantity, BigDecimal lineAmount) {
        this.orderNo = orderNo; this.sku = sku; this.productName = productName;
        this.unitPrice = unitPrice; this.quantity = quantity; this.lineAmount = lineAmount;
    }
    public String getOrderNo() { return orderNo; }
    public String getSku() { return sku; }
    public String getProductName() { return productName; }
    public BigDecimal getUnitPrice() { return unitPrice; }
    public int getQuantity() { return quantity; }
    public BigDecimal getLineAmount() { return lineAmount; }
}
