package org.juon;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.juon.jpashop.domain.Member;
import org.juon.jpashop.domain.Role;
import org.juon.jpashop.enums.RoleStatus;
import org.juon.jpashop.repository.MemberRepository;
import org.juon.jpashop.repository.RoleRepository;
import org.juon.jpashop.service.MemberService;
import org.juon.jpashop.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
//@ContextConfiguration(locations = "classpath:appConfig.xml")
@Transactional
public class MemberServiceTest {

	@Autowired MemberService service;
	@Autowired MemberRepository repository;
	@Autowired RoleService roleService;
	@Autowired RoleRepository roleRepository;
	
	@Test
	public void test() {
		//Given
		Role role = new Role();
		role.setRole(RoleStatus.ROLE_TEST);
		roleService.save(role);
		
		Role getRole = roleService.findById(role.getId());
		assertEquals(role, getRole);
		
		Member m = new Member();
		m.setName("name");
		m.setEmail("email");
		m.setPassword("password");
		m.getRoles().add(role);
		
		service.join(m);
		
		assertEquals(role, m.getRoles().get(0));
		
		/*
		Member member = new Member();
		member.setName("kim");
		member.setPassword("1234567");
		member.getRoles().add(role);
		
		//When
		Long saveId = service.join(member);
		assertEquals(member, repository.findOne(saveId));
		*/
		//Then
	}
	

}
