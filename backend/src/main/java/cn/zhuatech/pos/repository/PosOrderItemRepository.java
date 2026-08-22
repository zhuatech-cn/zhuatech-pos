/* Copyright 2026 Shanghai Rujing Zhihua Information Technology Co., Ltd. · https://www.zhuatech.cn/ */
package cn.zhuatech.pos.repository;
import cn.zhuatech.pos.model.PosOrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface PosOrderItemRepository extends JpaRepository<PosOrderItem, Long> {
    List<PosOrderItem> findByOrderNo(String orderNo);
}
