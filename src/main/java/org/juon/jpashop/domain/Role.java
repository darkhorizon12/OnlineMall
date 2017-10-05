package org.juon.jpashop.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.juon.jpashop.enums.RoleStatus;

@Entity
public class Role {
	
	@Id @GeneratedValue
	@Column(name = "ROLE_ID")
	private Long id;
	
	@Enumerated(EnumType.STRING)
	private RoleStatus roleStatus;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public RoleStatus getRole() {
		return roleStatus;
	}

	public void setRole(RoleStatus role) {
		this.roleStatus = role;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", role=" + roleStatus + "]";
	}
}
