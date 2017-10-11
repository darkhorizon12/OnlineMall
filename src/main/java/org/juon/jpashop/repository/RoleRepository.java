package org.juon.jpashop.repository;

import org.juon.jpashop.domain.Role;
import org.juon.jpashop.enums.RoleStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long>{
	
	Role findByRoleStatus(RoleStatus roleStatus);
}
