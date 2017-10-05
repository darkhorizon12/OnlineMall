package org.juon.jpashop.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.juon.jpashop.domain.Category;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class CategoryRepository {
	@PersistenceContext EntityManager em;
	
	public void save (Category category) {
		em.persist(category);
	}
	
	public Category findOne(Long id) {
		return em.find(Category.class, id);
	}
	
	public List<Category> findParent() {
		return em.createQuery("SELECT c FROM Category c WHERE c.parent IS NULL", Category.class)
				.getResultList();
	}
	
	public List<Category> findChildren(Long id) {
		return em.createQuery("SELECT c FROM Category c WHERE c.parent.id = :parentId", Category.class)
				.setParameter("parentId", id)
				.getResultList();
	}
}
