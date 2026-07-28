/* Copyright 2026 Shanghai Rujing Zhihua Information Technology Co., Ltd. */
package cn.zhuatech.pos.repository;
import cn.zhuatech.pos.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByMobile(String mobile);
    List<Member> findAllByOrderByTotalSpendDesc();
}
