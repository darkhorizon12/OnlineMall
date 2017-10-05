package org.juon.jpashop.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.juon.jpashop.addtional.OrderSearch;
import org.juon.jpashop.domain.Order;
import org.juon.jpashop.domain.QMember;
import org.juon.jpashop.domain.QOrder;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.mysema.query.BooleanBuilder;
import com.mysema.query.jpa.impl.JPAQuery;

@Repository
public class OrderRepository {
	
	@PersistenceContext EntityManager em;
	
	public void save(Order order) {
		em.persist(order);
	}
	
	public Order findOne(Long id) {
		return em.find(Order.class, id);
	}
	
	public List<Order> findAll(OrderSearch orderSearch) {
		JPAQuery query = new JPAQuery(em);
		QOrder qOrder = QOrder.order;
		QMember qMember = QMember.member;
		query = query.from(qOrder);
		
		BooleanBuilder builder = new BooleanBuilder();
		
		if (orderSearch.getOrderStatus() != null) {
			builder.and(qOrder.orderStatus.eq(orderSearch.getOrderStatus()));
		}
		
		if (StringUtils.hasText(orderSearch.getMemberName())) {
			query = query.join(qOrder.member, qMember);
			builder.and(qMember.name.eq(orderSearch.getMemberName()));
		}
		
		List<Order> listOrder = query.where(builder)
			.orderBy(qOrder.orderDate.desc())
			.list(qOrder);
		return listOrder;
		
		/* Criteria Query Source
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Order> cq = cb.createQuery(Order.class);
		Root<Order> o = cq.from(Order.class);
		
		List<Predicate> criteria = new ArrayList<Predicate>();
		
		if (orderSearch.getOrderStatus() != null) {
			Predicate status = 
					cb.equal(o.get("orderStatus"), orderSearch.getOrderStatus());
			criteria.add(status);
		}
		
		if (StringUtils.hasText(orderSearch.getMemberName())) {
			Join<Order, Member> m = o.join("member", JoinType.INNER);
			
			Predicate name = 
					cb.like(m.<String>get("name"), "%" + orderSearch.getMemberName() + "%");
			criteria.add(name);
		}
		
		cq.where(cb.and(criteria.toArray(new Predicate[criteria.size()])));
		TypedQuery<Order> query = 
				em.createQuery(cq).setMaxResults(1000);
		
		return query.getResultList();
		
		*/
	}
}
