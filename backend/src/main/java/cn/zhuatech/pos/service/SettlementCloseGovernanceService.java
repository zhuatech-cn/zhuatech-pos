/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.pos.service;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SettlementCloseGovernanceService {
    public Assessment assess(Request request) {
        long providerVariance = request.providerSettledCents() - request.expectedDigitalCents();
        long cashVariance = request.actualCashCents() - request.expectedCashCents();
        List<String> blockers = new ArrayList<>();
        List<String> actions = new ArrayList<>();
        if (Math.abs(providerVariance) > request.toleranceCents()) blockers.add("支付渠道结算差异超过容差");
        if (Math.abs(cashVariance) > request.toleranceCents()) blockers.add("钱箱实盘差异超过容差");
        if (!request.refundsApproved()) blockers.add("退款与撤销交易尚未全部审批");
        if (!request.chargebacksReviewed()) actions.add("完成拒付与争议交易复核");
        if (!request.shiftOwnerSignedOff()) actions.add("取得值班负责人交班签署");

        Decision decision = !blockers.isEmpty() ? Decision.HOLD
                : !actions.isEmpty() ? Decision.REVIEW : Decision.CLOSE;
        return new Assessment(request.shiftNo(), providerVariance, cashVariance, decision,
                List.copyOf(blockers), List.copyOf(actions));
    }

    public record Request(@NotBlank String shiftNo, @Min(0) long expectedDigitalCents,
                          @Min(0) long providerSettledCents, @Min(0) long expectedCashCents,
                          @Min(0) long actualCashCents, @Min(0) long toleranceCents,
                          boolean refundsApproved, boolean chargebacksReviewed,
                          boolean shiftOwnerSignedOff) {}
    public record Assessment(String shiftNo, long providerVarianceCents, long cashVarianceCents,
                             Decision decision, List<String> blockers, List<String> actions) {}
    public enum Decision { CLOSE, REVIEW, HOLD }
}
