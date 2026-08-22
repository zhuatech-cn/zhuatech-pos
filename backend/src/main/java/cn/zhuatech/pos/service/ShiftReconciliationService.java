/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.pos.service;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ShiftReconciliationService {
    public Result reconcile(Request request) {
        BigDecimal expectedDrawer = request.systemCash().add(request.openingFloat()).subtract(request.refundAmount());
        BigDecimal variance = request.countedCash().subtract(expectedDrawer);
        BigDecimal absoluteVariance = variance.abs();
        String status = absoluteVariance.compareTo(new BigDecimal("10")) <= 0 ? "BALANCED"
            : absoluteVariance.compareTo(new BigDecimal("500")) > 0 ? "BLOCK" : "REVIEW";
        List<String> actions = new ArrayList<>();
        if (!"BALANCED".equals(status)) actions.add("复核现金收款、退款单与备用金交接记录");
        if ("BLOCK".equals(status)) actions.add("暂停交班并通知门店经理复核");
        if (actions.isEmpty()) actions.add("完成交班并归档本班次对账单");
        return new Result(request.registerNo(), expectedDrawer, request.countedCash(),
            request.electronicPayments(), variance, status, !"BALANCED".equals(status), actions);
    }

    public record Request(@NotBlank String registerNo,
                          @DecimalMin("0") BigDecimal systemCash,
                          @DecimalMin("0") BigDecimal countedCash,
                          @DecimalMin("0") BigDecimal electronicPayments,
                          @DecimalMin("0") BigDecimal refundAmount,
                          @DecimalMin("0") BigDecimal openingFloat) {}
    public record Result(String registerNo, BigDecimal expectedDrawer, BigDecimal countedCash,
                         BigDecimal electronicPayments, BigDecimal variance, String status,
                         boolean managerReview, List<String> actions) {}
}
