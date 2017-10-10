package org.juon.jpashop.repository;

import java.util.stream.Stream;

import org.juon.jpashop.domain.item.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ItemRepositoryImpl extends JpaRepository<Item, Long>{
	
	@Query("SELECT i FROM Item i")
	Stream<Item> findAllItems();
	
	@Query("SELECT i FROM Item i WHERE i.price > :price")
	Stream<Item> findMoreExpensiveThanPrice(@Param("price") Double price);
}
