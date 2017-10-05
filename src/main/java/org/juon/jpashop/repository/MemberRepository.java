package org.juon.jpashop.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.juon.jpashop.domain.Member;
import org.springframework.stereotype.Repository;

@Repository
public class MemberRepository {
	@PersistenceContext	EntityManager em;
	
	public void save(Member member) {
		em.persist(member);
	}
	
	public Member findOne(Long id) {
		return em.find(Member.class, id);
	}
	
	public Member findByEmail(String email) {
		Member member = null;
		try {
			member = em.createQuery("SELECT m FROM Member m WHERE m.email = :email", Member.class)
						.setParameter("email", email)
						.getSingleResult();
		} catch (NoResultException e) {
			System.out.println("########## NORESULTEXCEPION CHECK@@@ ############");
		}
		return member;
	}

	public List<Member> findListByEmail(String email) {
		return em.createQuery("SELECT m FROM Member m WHERE m.email = :email", Member.class)
				.setParameter("email", email)
				.getResultList();
	}
	
	public List<Member> findAll() {
		return em.createQuery("SELECT m FROM Member m", Member.class)
				.getResultList();
	}
	
	public List<Member> findByName(String name) {
		return em.createQuery("SELECT m FROM Member m WHERE m.name = :name", Member.class)
				.setParameter("name", name)
				.getResultList();
	}
}
