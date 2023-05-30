package com.infitry.laboratory.persistence;

import com.infitry.laboratory.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
