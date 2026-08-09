/* Copyright 2026 上海如静知华信息科技有限公司 */
package cn.zhuatech.pos;

import cn.zhuatech.pos.service.PromotionMarginGuardService;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PromotionMarginGuardServiceTests {
    private final PromotionMarginGuardService service = new PromotionMarginGuardService();

    @Test
    void approvesPromotionWithHealthyMargin() {
        var result = service.evaluate(new PromotionMarginGuardService.Request(
            "SKU-1001", new BigDecimal("100"), new BigDecimal("55"),
            new BigDecimal("0.20"), new BigDecimal("2"), 100));

        assertEquals("APPROVE", result.decision());
        assertEquals(new BigDecimal("23.00"), result.unitMargin());
        assertEquals(new BigDecimal("2300.00"), result.campaignMargin());
    }

    @Test
    void blocksPromotionThatWouldCreateNegativeMargin() {
        var result = service.evaluate(new PromotionMarginGuardService.Request(
            "SKU-1002", new BigDecimal("100"), new BigDecimal("76"),
            new BigDecimal("0.30"), new BigDecimal("3"), 50));

        assertEquals("BLOCK", result.decision());
        assertEquals(new BigDecimal("-9.00"), result.unitMargin());
    }
}
