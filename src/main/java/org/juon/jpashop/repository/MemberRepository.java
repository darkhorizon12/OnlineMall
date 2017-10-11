package org.juon.jpashop.repository;

import java.util.stream.Stream;

import org.juon.jpashop.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long>{
	
	Member findByEmail(String email);

	Stream<Member> findByName(String name);
}
