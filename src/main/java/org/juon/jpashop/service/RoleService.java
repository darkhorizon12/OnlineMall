package org.juon.jpashop.service;

import java.util.List;

import org.juon.jpashop.domain.Role;
import org.juon.jpashop.enums.RoleStatus;
import org.juon.jpashop.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RoleService {
	
	@Autowired 
	RoleRepository roleRepository;
	
	public Role findByRole(RoleStatus roleStatus) {
		return roleRepository.findByRoleStatus(roleStatus);
	}
	
	public Role findById(Long id) {
		return roleRepository.findOne(id);
	}
	
	public List<Role> findAll() {
		return roleRepository.findAll();
	}
	
	public void save(Role role) {
		roleRepository.save(role);
	}
}
