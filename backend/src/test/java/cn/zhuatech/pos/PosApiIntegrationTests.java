/* Copyright 2026 Shanghai Rujing Zhihua Information Technology Co., Ltd. */
package cn.zhuatech.pos;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@SpringBootTest
@AutoConfigureMockMvc
class PosApiIntegrationTests {
    @Autowired MockMvc mvc;
    @Test void cashierCanReadProductsAndCheckout() throws Exception {
        String token = login("cashier", "Demo@2026", "CASHIER");
        mvc.perform(get("/api/pos/products").header("Authorization", "Bearer " + token))
            .andExpect(status().isOk()).andExpect(jsonPath("$.data[0].sku").isNotEmpty());
        mvc.perform(post("/api/pos/checkout").header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"storeName\":\"上海静安旗舰店\",\"registerNo\":\"POS-02\",\"paymentMethod\":\"WECHAT\",\"discount\":2,\"items\":[{\"sku\":\"FD-10021\",\"quantity\":2}]}"))
            .andExpect(status().isOk()).andExpect(jsonPath("$.data.payable").value(42.0));
    }
    @Test void managerCanReadDashboard() throws Exception {
        String token = login("manager", "Demo@2026", "MANAGER");
        mvc.perform(get("/api/pos/dashboard").header("Authorization", "Bearer " + token))
            .andExpect(status().isOk()).andExpect(jsonPath("$.data.summary.todayOrders").value(1147));
    }
    @Test void anonymousRequestIsRejected() throws Exception { mvc.perform(get("/api/pos/dashboard")).andExpect(status().isForbidden()); }
    private String login(String username, String password, String role) throws Exception {
        String body = mvc.perform(post("/api/auth/login").contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\":\"" + username + "\",\"password\":\"" + password + "\"}"))
            .andExpect(status().isOk()).andExpect(jsonPath("$.data.user.role").value(role))
            .andReturn().getResponse().getContentAsString();
        return JsonPath.read(body, "$.data.token");
    }
}
