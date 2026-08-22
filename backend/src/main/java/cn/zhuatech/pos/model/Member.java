/* Copyright 2026 Shanghai Rujing Zhihua Information Technology Co., Ltd. · https://www.zhuatech.cn/ */
package cn.zhuatech.pos.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "pos_member")
public class Member extends BaseEntity {
    @Column(nullable = false, unique = true, length = 24) private String memberNo;
    @Column(nullable = false, length = 50) private String name;
    @Column(nullable = false, unique = true, length = 20) private String mobile;
    @Column(nullable = false, length = 20) private String level;
    @Column(nullable = false) private int points;
    @Column(nullable = false, precision = 12, scale = 2) private BigDecimal balance;
    @Column(nullable = false, precision = 14, scale = 2) private BigDecimal totalSpend;
    protected Member() {}
    public Member(String memberNo, String name, String mobile, String level, int points, BigDecimal balance, BigDecimal totalSpend) {
        this.memberNo = memberNo; this.name = name; this.mobile = mobile; this.level = level;
        this.points = points; this.balance = balance; this.totalSpend = totalSpend;
    }
    public String getMemberNo() { return memberNo; }
    public String getName() { return name; }
    public String getMobile() { return mobile; }
    public String getLevel() { return level; }
    public int getPoints() { return points; }
    public BigDecimal getBalance() { return balance; }
    public BigDecimal getTotalSpend() { return totalSpend; }
}
