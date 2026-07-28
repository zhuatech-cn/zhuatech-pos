/* Copyright 2026 Shanghai Rujing Zhihua Information Technology Co., Ltd. */
package cn.zhuatech.pos.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "pos_cashier_shift")
public class CashierShift extends BaseEntity {
    public enum Status { OPEN, CLOSED }
    @Column(nullable = false, unique = true, length = 32) private String shiftNo;
    @Column(nullable = false, length = 80) private String storeName;
    @Column(nullable = false, length = 24) private String registerNo;
    @Column(nullable = false, length = 40) private String cashierName;
    @Enumerated(EnumType.STRING) @Column(nullable = false, length = 12) private Status status;
    @Column(nullable = false) private LocalDateTime openedAt;
    private LocalDateTime closedAt;
    @Column(nullable = false, precision = 12, scale = 2) private BigDecimal openingCash;
    @Column(nullable = false, precision = 12, scale = 2) private BigDecimal expectedCash;
    @Column(nullable = false) private int orderCount;
    protected CashierShift() {}
    public CashierShift(String shiftNo, String storeName, String registerNo, String cashierName, Status status,
                        LocalDateTime openedAt, BigDecimal openingCash, BigDecimal expectedCash, int orderCount) {
        this.shiftNo = shiftNo; this.storeName = storeName; this.registerNo = registerNo;
        this.cashierName = cashierName; this.status = status; this.openedAt = openedAt;
        this.openingCash = openingCash; this.expectedCash = expectedCash; this.orderCount = orderCount;
    }
    public String getShiftNo() { return shiftNo; }
    public String getStoreName() { return storeName; }
    public String getRegisterNo() { return registerNo; }
    public String getCashierName() { return cashierName; }
    public Status getStatus() { return status; }
    public LocalDateTime getOpenedAt() { return openedAt; }
    public LocalDateTime getClosedAt() { return closedAt; }
    public BigDecimal getOpeningCash() { return openingCash; }
    public BigDecimal getExpectedCash() { return expectedCash; }
    public int getOrderCount() { return orderCount; }
}
