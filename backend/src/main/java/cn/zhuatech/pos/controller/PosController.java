/* Copyright 2026 Shanghai Rujing Zhihua Information Technology Co., Ltd. */
package cn.zhuatech.pos.controller;
import cn.zhuatech.pos.common.ApiResponse;
import cn.zhuatech.pos.dto.PosDto.*;
import cn.zhuatech.pos.model.*;
import cn.zhuatech.pos.repository.*;
import cn.zhuatech.pos.service.PosService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/pos")
public class PosController {
    private final PosService service; private final ProductRepository products; private final PosOrderRepository orders;
    private final StoreRepository stores; private final MemberRepository members; private final CashierShiftRepository shifts;
    public PosController(PosService service, ProductRepository products, PosOrderRepository orders,
                         StoreRepository stores, MemberRepository members, CashierShiftRepository shifts) {
        this.service = service; this.products = products; this.orders = orders; this.stores = stores; this.members = members; this.shifts = shifts;
    }
    @GetMapping("/products") public ApiResponse<List<Product>> products() { return ApiResponse.ok(products.findAllByOrderByCategoryAscNameAsc()); }
    @GetMapping("/orders") public ApiResponse<List<PosOrder>> orders() { return ApiResponse.ok(orders.findTop30ByOrderByCreatedAtDesc()); }
    @PostMapping("/checkout") @PreAuthorize("hasAnyRole('ADMIN','MANAGER','CASHIER')")
    public ApiResponse<CheckoutResult> checkout(@Valid @RequestBody CheckoutRequest request) {
        return ApiResponse.ok("收款成功", service.checkout(request));
    }
    @GetMapping("/dashboard") @PreAuthorize("hasAnyRole('ADMIN','MANAGER','AUDITOR')")
    public ApiResponse<DashboardView> dashboard() { return ApiResponse.ok(service.dashboard()); }
    @GetMapping("/stores") public ApiResponse<List<Store>> stores() { return ApiResponse.ok(stores.findAllByOrderByTodaySalesDesc()); }
    @GetMapping("/members") public ApiResponse<List<Member>> members() { return ApiResponse.ok(members.findAllByOrderByTotalSpendDesc()); }
    @GetMapping("/shifts") public ApiResponse<List<CashierShift>> shifts() { return ApiResponse.ok(shifts.findAll()); }
}
