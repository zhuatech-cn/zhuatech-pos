/* Copyright 2026 Shanghai Rujing Zhihua Information Technology Co., Ltd. · https://www.zhuatech.cn/ */
package cn.zhuatech.pos.model;

import jakarta.persistence.*;

@Entity
@Table(name = "pos_user")
public class UserAccount extends BaseEntity {
    public enum Role { ADMIN, MANAGER, CASHIER, AUDITOR }
    @Column(nullable = false, unique = true, length = 32) private String username;
    @Column(nullable = false) private String password;
    @Column(nullable = false, length = 50) private String fullName;
    @Enumerated(EnumType.STRING) @Column(nullable = false, length = 20) private Role role;
    @Column(length = 80) private String storeName;
    @Column(nullable = false) private boolean enabled = true;
    protected UserAccount() {}
    public UserAccount(String username, String password, String fullName, Role role, String storeName) {
        this.username = username; this.password = password; this.fullName = fullName;
        this.role = role; this.storeName = storeName;
    }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getFullName() { return fullName; }
    public Role getRole() { return role; }
    public String getStoreName() { return storeName; }
    public boolean isEnabled() { return enabled; }
}
