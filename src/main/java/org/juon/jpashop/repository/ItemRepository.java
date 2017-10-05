package org.juon.jpashop.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.juon.jpashop.domain.CategoryItem;
import org.juon.jpashop.domain.item.Item;
import org.juon.jpashop.utils.Pagination;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class ItemRepository {
	
	@PersistenceContext EntityManager em;
	
	public void save(Item item) {
		if(item.getId() == null) {
			em.persist(item);
		} else {
			em.merge(item);
		}
	}
	
	public void saveCategoryItem(CategoryItem categoryItem) {
		em.persist(categoryItem);
	}
	
	public Item findOne(Long id) {
		return em.find(Item.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Item> findAll(Pagination pagination) {
		Query query = em.createQuery("SELECT i FROM Item i", Item.class);
		query.setFirstResult(pagination.getStartPos());
		query.setMaxResults(pagination.getPageSize());
		
		Query totalQuery = em.createQuery("SELECT COUNT(i.id) FROM Item i");
		Long totalCount = (Long) totalQuery.getSingleResult();
		System.out.println("BEFORE PAGINATIoN >>> " + pagination);
		pagination.setTotalCount(totalCount.intValue());
		System.out.println("AFTER PAGINATIoN >>> " + pagination);
		
		return query.getResultList();
	}
	
	public void deleteItem(Item item) {
		em.remove(item);
	}
}
