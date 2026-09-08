/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.pos.controller;
import cn.zhuatech.pos.common.ApiResponse;import cn.zhuatech.pos.service.RefundVoidGovernanceService;import jakarta.validation.Valid;import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/enterprise/pos") public class RefundVoidGovernanceController{
 private final RefundVoidGovernanceService service;public RefundVoidGovernanceController(RefundVoidGovernanceService service){this.service=service;}
 @PostMapping("/refund-void")public ApiResponse<RefundVoidGovernanceService.Assessment> assess(@Valid @RequestBody RefundVoidGovernanceService.Request request){return ApiResponse.ok("退款作废评估完成",service.assess(request));}
}
