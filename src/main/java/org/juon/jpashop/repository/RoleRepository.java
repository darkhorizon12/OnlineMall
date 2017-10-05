package org.juon.jpashop.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.juon.jpashop.domain.Role;
import org.juon.jpashop.enums.RoleStatus;
import org.springframework.stereotype.Repository;

@Repository
public class RoleRepository {
	@PersistenceContext EntityManager em;
	
	public void save(Role role) {
		em.persist(role);
	}
	
	public List<Role> findAll() {
		return em.createQuery("SELECT r FROM Role r", Role.class)
				.getResultList();
	}
	
	public Role findById(Long id) {
		return em.createQuery("SELECT r FROM Role r WHERE r.id = :id", Role.class)
				.setParameter("id", id)
				.getSingleResult();
	}
	
	public Role findByRole(RoleStatus roleStatus) {
		return em.createQuery("SELECT r FROM Role r WHERE r.roleStatus = :roleStatus", Role.class)
				.setParameter("roleStatus", roleStatus)
				.getSingleResult();
	}
}
