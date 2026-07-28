/* Copyright 2026 Shanghai Rujing Zhihua Information Technology Co., Ltd. */
package cn.zhuatech.pos.repository;
import cn.zhuatech.pos.model.PosOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;
public interface PosOrderRepository extends JpaRepository<PosOrder, Long> {
    List<PosOrder> findTop30ByOrderByCreatedAtDesc();
    long countByStatus(PosOrder.Status status);
}
