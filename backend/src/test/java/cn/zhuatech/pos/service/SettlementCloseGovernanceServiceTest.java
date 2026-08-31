/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.pos.service;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class SettlementCloseGovernanceServiceTest {
    private final SettlementCloseGovernanceService service = new SettlementCloseGovernanceService();

    @Test void closesBalancedShift() {
        var result = service.assess(new SettlementCloseGovernanceService.Request(
                "SHIFT-001", 500_000, 500_010, 80_000, 79_990, 20, true, true, true));
        assertThat(result.decision()).isEqualTo(SettlementCloseGovernanceService.Decision.CLOSE);
        assertThat(result.blockers()).isEmpty();
    }

    @Test void holdsMaterialSettlementVariance() {
        var result = service.assess(new SettlementCloseGovernanceService.Request(
                "SHIFT-002", 500_000, 496_000, 80_000, 78_000, 100, false, false, false));
        assertThat(result.decision()).isEqualTo(SettlementCloseGovernanceService.Decision.HOLD);
        assertThat(result.blockers()).hasSize(3);
        assertThat(result.actions()).hasSize(2);
    }
}
