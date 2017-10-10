package org.juon.jpashop.repository;

import java.util.stream.Stream;

import org.juon.jpashop.domain.Cart;
import org.juon.jpashop.domain.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long>{
	Stream<Cart> findByMember(Member member);
	Page<Cart> findByMember(Member member, Pageable pageable);
	
}
