package org.juon.jpashop.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.juon.jpashop.domain.Address;
import org.juon.jpashop.domain.Member;
import org.juon.jpashop.domain.Role;
import org.juon.jpashop.service.MemberService;
import org.juon.jpashop.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/members")
public class MemberController {
	
	@Autowired 
	MemberService memberService;

	@Autowired 
	RoleService roleService;
	
	public String list(Model model) {
		List<Member> members = 
				memberService.findMembers();
		model.addAttribute("members", members);
		
		return "/members/memberList";
	}
	
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String create(Model model) {
		List<Role> roles = roleService.findAll();
		model.addAttribute("roles", roles);
		
		return "/members/createMemberForm";
	}
	
	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public String save(Member member, String city, String street, String zipcode, HttpServletRequest request) {
		Address address = new Address(city, street, zipcode);
		member.setAddress(address);
		
		String[] roleIds = request.getParameterValues("role");
		for (String roleId : roleIds) {
			member.getRoles().add(roleService.findById(Long.valueOf(roleId)));
		}
		
		memberService.join(member);
		
		return "redirect:/";
	}
}
