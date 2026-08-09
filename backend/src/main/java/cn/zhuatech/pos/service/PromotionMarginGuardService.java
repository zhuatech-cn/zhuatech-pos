/* Copyright 2026 上海如静知华信息科技有限公司 */
package cn.zhuatech.pos.service;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Service
public class PromotionMarginGuardService {
    public Result evaluate(Request request) {
        BigDecimal netUnitRevenue = request.listPrice()
            .multiply(BigDecimal.ONE.subtract(request.discountRate()))
            .setScale(2, RoundingMode.HALF_UP);
        BigDecimal unitMargin = netUnitRevenue.subtract(request.unitCost())
            .subtract(request.loyaltyPointCost()).setScale(2, RoundingMode.HALF_UP);
        BigDecimal marginRate = netUnitRevenue.signum() == 0 ? BigDecimal.ZERO
            : unitMargin.divide(netUnitRevenue, 4, RoundingMode.HALF_UP);
        BigDecimal campaignMargin = unitMargin.multiply(BigDecimal.valueOf(request.quantity()))
            .setScale(2, RoundingMode.HALF_UP);

        String decision = unitMargin.signum() < 0 || marginRate.compareTo(new BigDecimal("0.08")) < 0 ? "BLOCK"
            : marginRate.compareTo(new BigDecimal("0.15")) < 0 ? "REVIEW" : "APPROVE";
        List<String> actions = new ArrayList<>();
        if ("BLOCK".equals(decision)) actions.add("阻止活动下发并要求调整折扣或积分抵扣");
        if ("REVIEW".equals(decision)) actions.add("由门店经理复核活动引流价值与库存压力");
        if ("APPROVE".equals(decision)) actions.add("允许活动下发并持续监测实际毛利");
        return new Result(request.sku(), netUnitRevenue, unitMargin, marginRate,
            campaignMargin, decision, actions);
    }

    public record Request(@NotBlank String sku, @DecimalMin("0.01") BigDecimal listPrice,
                          @DecimalMin("0") BigDecimal unitCost,
                          @DecimalMin("0") @DecimalMax("1") BigDecimal discountRate,
                          @DecimalMin("0") BigDecimal loyaltyPointCost,
                          @Min(1) int quantity) {}

    public record Result(String sku, BigDecimal netUnitRevenue, BigDecimal unitMargin,
                         BigDecimal marginRate, BigDecimal campaignMargin,
                         String decision, List<String> actions) {}
}
