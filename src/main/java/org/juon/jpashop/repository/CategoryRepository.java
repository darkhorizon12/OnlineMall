package org.juon.jpashop.repository;

import java.util.stream.Stream;

import org.juon.jpashop.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CategoryRepository extends JpaRepository<Category, Long>{
	
	@Query("SELECT c FROM Category c WHERE c.parent IS NULL")
	Stream<Category> findParent();
	
	@Query("SELECT c FROM Category c WHERE c.parent.id = :parentId")
	Stream<Category> findChildren(@Param("parentId") Long id);
}
