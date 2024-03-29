
package com.infitry.laboratory.persistence.jpa;

import com.infitry.laboratory.entity.MemberGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<MemberGroup, Long> {
}
