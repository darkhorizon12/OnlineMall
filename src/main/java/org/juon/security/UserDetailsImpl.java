package org.juon.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.juon.jpashop.domain.Member;
import org.juon.jpashop.domain.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;


public class UserDetailsImpl extends User{
	
	private static final long serialVersionUID = 1L;
	
	private String email;
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public UserDetailsImpl(Member member) {
		super(member.getEmail(), member.getPassword(), authorities(member));
		this.email = member.getEmail();
	}

	private static Collection<? extends GrantedAuthority> authorities(Member member) {
		List<GrantedAuthority> authorities = new ArrayList<>();
		
		for (Role role : member.getRoles()) {
			authorities.add(new SimpleGrantedAuthority(String.valueOf(role.getRole())));
		}
		
		return authorities;
	}
}
