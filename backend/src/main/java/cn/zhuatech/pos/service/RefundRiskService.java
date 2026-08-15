/* Copyright 2026 上海如静知华信息科技有限公司 */
package cn.zhuatech.pos.service;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Service
public class RefundRiskService {
    public Result assess(Request request) {
        BigDecimal refundRate = request.salesCount() == 0 ? BigDecimal.ZERO
            : BigDecimal.valueOf(request.refundCount()).divide(BigDecimal.valueOf(request.salesCount()), 4, RoundingMode.HALF_UP);
        BigDecimal refundAmountRate = request.salesAmount().signum() == 0 ? BigDecimal.ZERO
            : request.refundAmount().divide(request.salesAmount(), 4, RoundingMode.HALF_UP);
        int score = Math.min(40, refundRate.multiply(BigDecimal.valueOf(400)).intValue());
        score += Math.min(30, refundAmountRate.multiply(BigDecimal.valueOf(300)).intValue());
        score += Math.min(15, request.voidCount() * 3);
        score += Math.min(15, request.manualDiscountCount() * 2);
        score = Math.min(100, score);

        String decision = score >= 65 ? "REVIEW_SHIFT" : score >= 35 ? "VERIFY_SAMPLE" : "PASS";
        List<String> reasons = new ArrayList<>();
        if (refundRate.compareTo(new BigDecimal("0.08")) >= 0) reasons.add("退款笔数占比偏高");
        if (refundAmountRate.compareTo(new BigDecimal("0.10")) >= 0) reasons.add("退款金额占比偏高");
        if (request.voidCount() >= 4) reasons.add("作废交易次数异常");
        if (request.manualDiscountCount() >= 6) reasons.add("人工折扣操作较多");
        if (reasons.isEmpty()) reasons.add("当前班次退款与操作指标正常");
        return new Result(request.storeCode(), request.shiftNo(), refundRate, refundAmountRate, score, decision, reasons);
    }

    public record Request(@NotBlank String storeCode, @NotBlank String shiftNo,
                          @Min(0) int salesCount, @Min(0) int refundCount,
                          @DecimalMin("0") BigDecimal refundAmount,
                          @DecimalMin("0") BigDecimal salesAmount,
                          @Min(0) int voidCount, @Min(0) int manualDiscountCount) {}
    public record Result(String storeCode, String shiftNo, BigDecimal refundRate, BigDecimal refundAmountRate,
                         int riskScore, String decision, List<String> reasons) {}
}
