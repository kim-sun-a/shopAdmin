package com.shop.shopadmin.repository;

import com.shop.shopadmin.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
