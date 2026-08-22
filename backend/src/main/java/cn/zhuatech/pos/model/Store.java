/* Copyright 2026 Shanghai Rujing Zhihua Information Technology Co., Ltd. · https://www.zhuatech.cn/ */
package cn.zhuatech.pos.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "pos_store")
public class Store extends BaseEntity {
    public enum Status { OPEN, PREPARING, CLOSED }
    @Column(nullable = false, unique = true, length = 24) private String storeCode;
    @Column(nullable = false, length = 80) private String name;
    @Column(nullable = false, length = 40) private String city;
    @Column(nullable = false, length = 120) private String address;
    @Enumerated(EnumType.STRING) @Column(nullable = false, length = 16) private Status status;
    @Column(nullable = false) private int registerCount;
    @Column(nullable = false, precision = 14, scale = 2) private BigDecimal todaySales;
    @Column(nullable = false) private int todayOrders;
    protected Store() {}
    public Store(String storeCode, String name, String city, String address, Status status,
                 int registerCount, BigDecimal todaySales, int todayOrders) {
        this.storeCode = storeCode; this.name = name; this.city = city; this.address = address;
        this.status = status; this.registerCount = registerCount; this.todaySales = todaySales; this.todayOrders = todayOrders;
    }
    public String getStoreCode() { return storeCode; }
    public String getName() { return name; }
    public String getCity() { return city; }
    public String getAddress() { return address; }
    public Status getStatus() { return status; }
    public int getRegisterCount() { return registerCount; }
    public BigDecimal getTodaySales() { return todaySales; }
    public int getTodayOrders() { return todayOrders; }
}
