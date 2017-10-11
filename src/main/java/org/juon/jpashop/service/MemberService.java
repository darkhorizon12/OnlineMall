package org.juon.jpashop.service;

import java.util.List;

import org.juon.jpashop.domain.Member;
import org.juon.jpashop.repository.MemberRepository;
import org.juon.jpashop.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MemberService {
	@Autowired
	MemberRepository memberRepository;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	PasswordEncoder passwordEncoder;
	
	public Long join(Member member) {
		validateDuplicateMember(member);
		
		member.setPassword(passwordEncoder.encode(member.getPassword()));
		memberRepository.save(member);
		return member.getId();
	}
	
	private void validateDuplicateMember(Member member) {
		Member findMember = 
				memberRepository.findByEmail(member.getEmail());
		
		if (findMember != null) {
			throw new IllegalStateException("Already exists!");
		}
	}
	
	public List<Member> findMembers() {
		return memberRepository.findAll();
	}
	
	public Member findOne(Long id) {
		return memberRepository.findOne(id);
	}
	
	public Member findByEmail(String email) {
		return memberRepository.findByEmail(email);
	}
}
