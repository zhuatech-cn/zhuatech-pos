/* Copyright 2026 Shanghai Rujing Zhihua Information Technology Co., Ltd. */
package cn.zhuatech.pos.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "pos_product")
public class Product extends BaseEntity {
    public enum Status { ACTIVE, LOW_STOCK, OFF_SHELF }
    @Column(nullable = false, unique = true, length = 40) private String sku;
    @Column(nullable = false, unique = true, length = 40) private String barcode;
    @Column(nullable = false, length = 120) private String name;
    @Column(nullable = false, length = 40) private String category;
    @Column(nullable = false, precision = 12, scale = 2) private BigDecimal price;
    @Column(nullable = false, precision = 12, scale = 2) private BigDecimal memberPrice;
    @Column(nullable = false) private int stock;
    @Enumerated(EnumType.STRING) @Column(nullable = false, length = 20) private Status status;
    @Column(nullable = false, length = 20) private String colorCode;
    protected Product() {}
    public Product(String sku, String barcode, String name, String category, BigDecimal price,
                   BigDecimal memberPrice, int stock, Status status, String colorCode) {
        this.sku = sku; this.barcode = barcode; this.name = name; this.category = category;
        this.price = price; this.memberPrice = memberPrice; this.stock = stock;
        this.status = status; this.colorCode = colorCode;
    }
    public void reduceStock(int quantity) {
        if (quantity < 1 || stock < quantity) throw new IllegalArgumentException("库存不足");
        stock -= quantity;
        if (stock < 10) status = Status.LOW_STOCK;
    }
    public String getSku() { return sku; }
    public String getBarcode() { return barcode; }
    public String getName() { return name; }
    public String getCategory() { return category; }
    public BigDecimal getPrice() { return price; }
    public BigDecimal getMemberPrice() { return memberPrice; }
    public int getStock() { return stock; }
    public Status getStatus() { return status; }
    public String getColorCode() { return colorCode; }
}
