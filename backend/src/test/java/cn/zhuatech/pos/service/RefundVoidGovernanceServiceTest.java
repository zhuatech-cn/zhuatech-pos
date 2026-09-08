/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.pos.service;
import org.junit.jupiter.api.Test;import java.math.BigDecimal;import static org.assertj.core.api.Assertions.assertThat;
class RefundVoidGovernanceServiceTest{
 private final RefundVoidGovernanceService service=new RefundVoidGovernanceService();
 private RefundVoidGovernanceService.Request request(boolean fraud,boolean archive){return new RefundVoidGovernanceService.Request("R-1","cashier","manager",new BigDecimal("100"),new BigDecimal("100"),true,true,true,true,true,true,true,true,true,false,true,fraud,archive);}
 @Test void refundsControlledRequest(){assertThat(service.assess(request(true,true)).decision()).isEqualTo(RefundVoidGovernanceService.Decision.REFUND);}
 @Test void reviewsFraudAndArchiveActions(){var a=service.assess(request(false,false));assertThat(a.decision()).isEqualTo(RefundVoidGovernanceService.Decision.REVIEW);assertThat(a.actions()).hasSize(2);}
 @Test void blocksOfflineOverRefund(){var r=new RefundVoidGovernanceService.Request("R-2","u1","u1",new BigDecimal("3000"),new BigDecimal("1000"),true,false,false,false,false,false,false,false,false,true,false,false,false);var a=service.assess(r);assertThat(a.decision()).isEqualTo(RefundVoidGovernanceService.Decision.BLOCKED);assertThat(a.riskLevel()).isEqualTo(RefundVoidGovernanceService.RiskLevel.HIGH);assertThat(a.blockers()).hasSizeGreaterThanOrEqualTo(9);}
}
