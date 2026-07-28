/* Copyright 2026 Shanghai Rujing Zhihua Information Technology Co., Ltd. */
package cn.zhuatech.pos.repository;
import cn.zhuatech.pos.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface StoreRepository extends JpaRepository<Store, Long> {
    List<Store> findAllByOrderByTodaySalesDesc();
}
