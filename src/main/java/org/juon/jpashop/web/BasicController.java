package org.juon.jpashop.web;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class BasicController {
	private final int PAGE_SIZE = 3;
	
	protected Pageable createPage(Integer page) {
		if (page == null) {
			return new PageRequest(0, PAGE_SIZE);
		} else {
			return new PageRequest(page - 1, PAGE_SIZE);
		}
	}
	
	protected String createPagination(Page<?> page) {
		StringBuilder sb = new StringBuilder();
		sb.append("<ul class=\"pagination\">");
		if (!page.isFirst()) {
			sb.append("<li><a href=\"?page=1\">First</a></li>");
		}
		if (page.hasPrevious()) {
			sb.append("<li><a href=\"?page=").append(page.getNumber()).append("\">&lt;&lt;</a></li>");
		}
		sb.append("<li class=\"active\"><a href=\"#this\">").append(page.getNumber() + 1).append("</a></li>");
		if (page.hasNext()) {
			sb.append("<li><a href=\"?page=").append(page.getNumber() + 2).append("\">&gt;&gt;</a></li>");
		}
		if (!page.isLast()) {
			sb.append("<li><a href=\"?page=").append(page.getTotalPages()).append("\">Last</a></li>");
		}
		
		return sb.toString();
	}
}
