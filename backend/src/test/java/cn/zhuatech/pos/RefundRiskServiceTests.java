/* Copyright 2026 上海如静知华信息科技有限公司 */
package cn.zhuatech.pos;

import cn.zhuatech.pos.service.RefundRiskService;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.assertj.core.api.Assertions.assertThat;

class RefundRiskServiceTests {
    private final RefundRiskService service = new RefundRiskService();

    @Test void flagsAbnormalRefundShift() {
        var result = service.assess(new RefundRiskService.Request("S001", "SHIFT-08", 100, 16,
            new BigDecimal("18000"), new BigDecimal("90000"), 6, 9));
        assertThat(result.decision()).isEqualTo("REVIEW_SHIFT");
        assertThat(result.reasons()).hasSizeGreaterThanOrEqualTo(3);
    }

    @Test void passesNormalShift() {
        var result = service.assess(new RefundRiskService.Request("S001", "SHIFT-09", 200, 2,
            new BigDecimal("300"), new BigDecimal("80000"), 0, 1));
        assertThat(result.decision()).isEqualTo("PASS");
    }
}
