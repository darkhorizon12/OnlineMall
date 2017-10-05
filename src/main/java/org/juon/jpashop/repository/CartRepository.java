package org.juon.jpashop.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.juon.jpashop.domain.Cart;
import org.juon.jpashop.domain.Member;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class CartRepository {
	@PersistenceContext EntityManager em;
	
	public void save(Cart cart) {
		em.persist(cart);
	}
	
	public Cart findOne(Long id) {
		return em.find(Cart.class, id);
	}
	
	public List<Cart> findAllByMember(Member member) {
		return em.createQuery("SELECT c FROM Cart c WHERE c.member = :member", Cart.class)
				.setParameter("member", member)
				.getResultList();
	}
	
	public void remove(Cart cart) {
		em.remove(cart);
	}
}
