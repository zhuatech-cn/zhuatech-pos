/* Copyright 2026 Shanghai Rujing Zhihua Information Technology Co., Ltd. · https://www.zhuatech.cn/ */
package cn.zhuatech.pos.repository;
import cn.zhuatech.pos.model.CashierShift;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;
public interface CashierShiftRepository extends JpaRepository<CashierShift, Long> {
    Optional<CashierShift> findFirstByCashierNameAndStatusOrderByOpenedAtDesc(String cashierName, CashierShift.Status status);
}
