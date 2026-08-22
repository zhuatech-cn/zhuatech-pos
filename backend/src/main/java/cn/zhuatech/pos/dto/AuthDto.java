/* Copyright 2026 Shanghai Rujing Zhihua Information Technology Co., Ltd. · https://www.zhuatech.cn/ */
package cn.zhuatech.pos.dto;

import cn.zhuatech.pos.model.UserAccount;
import jakarta.validation.constraints.NotBlank;

public final class AuthDto {
    private AuthDto() {}
    public record LoginRequest(@NotBlank(message = "请输入用户名") String username,
                               @NotBlank(message = "请输入密码") String password) {}
    public record UserView(Long id, String username, String fullName, String role, String storeName) {
        public static UserView from(UserAccount user) {
            return new UserView(user.getId(), user.getUsername(), user.getFullName(), user.getRole().name(), user.getStoreName());
        }
    }
    public record LoginResponse(String token, UserView user) {}
}
