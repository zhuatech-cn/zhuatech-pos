/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.pos.service;
import jakarta.validation.constraints.*;import org.springframework.stereotype.Service;
import java.math.BigDecimal;import java.util.*;
@Service
public class RefundVoidGovernanceService{
 public Assessment assess(Request r){
  List<String> blockers=new ArrayList<>();List<String> actions=new ArrayList<>();
  if(!r.originalTransactionVerified())blockers.add("原交易不存在或不可退款");
  if(r.refundAmount().compareTo(r.refundableAmount())>0)blockers.add("退款金额超过原交易可退余额");
  if(!r.originalTenderUsed()&&!r.alternateTenderApproved())blockers.add("非原路退款未获得授权");
  if(!r.reasonAndEvidenceComplete())blockers.add("退款/作废原因及证据不完整");
  if(!r.managerApproved())blockers.add("门店负责人尚未审批");
  if(r.operatorId().equals(r.approverId()))blockers.add("操作员不得审批自己的退款或作废");
  if(!r.inventoryReversalPlanned())blockers.add("商品库存冲销未生成");
  if(!r.memberBenefitReversalPlanned())blockers.add("会员积分或权益冲销未生成");
  if(!r.taxDocumentHandled())blockers.add("发票或税务凭证尚未处理");
  if(!r.idempotencyKeyRegistered())blockers.add("退款指令缺少幂等键");
  if(r.offlineTransaction()&&!r.offlineReconciled())blockers.add("离线原交易尚未完成联网对账");
  if(!r.fraudSignalsReviewed())actions.add("执行退款欺诈信号复核");
  if(!r.auditEvidenceArchived())actions.add("归档小票、审批和支付回执");
  RiskLevel risk=r.refundAmount().compareTo(new BigDecimal("2000"))>=0||r.offlineTransaction()||!r.originalTenderUsed()?RiskLevel.HIGH:RiskLevel.NORMAL;
  Decision decision=!blockers.isEmpty()?Decision.BLOCKED:!actions.isEmpty()?Decision.REVIEW:Decision.REFUND;
  String route=risk==RiskLevel.HIGH?"店长→区域财务→风控":"店长";
  return new Assessment(r.requestNo(),decision,risk,route,List.copyOf(blockers),List.copyOf(actions));
 }
 public record Request(@NotBlank String requestNo,@NotBlank String operatorId,@NotBlank String approverId,
  @NotNull @DecimalMin("0.01") BigDecimal refundAmount,@NotNull @DecimalMin("0.00") BigDecimal refundableAmount,
  boolean originalTransactionVerified,boolean originalTenderUsed,boolean alternateTenderApproved,
  boolean reasonAndEvidenceComplete,boolean managerApproved,boolean inventoryReversalPlanned,
  boolean memberBenefitReversalPlanned,boolean taxDocumentHandled,boolean idempotencyKeyRegistered,
  boolean offlineTransaction,boolean offlineReconciled,boolean fraudSignalsReviewed,boolean auditEvidenceArchived){}
 public record Assessment(String requestNo,Decision decision,RiskLevel riskLevel,String approvalRoute,List<String> blockers,List<String> actions){}
 public enum Decision{REFUND,REVIEW,BLOCKED}public enum RiskLevel{NORMAL,HIGH}
}
