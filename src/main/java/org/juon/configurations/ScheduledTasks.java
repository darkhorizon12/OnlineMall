package org.juon.configurations;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.juon.jpashop.domain.Member;
import org.juon.jpashop.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {
	
	// Add Annotation @EnableScheduling on Application.java 
	
	@Autowired MemberService memberService;
	
	private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);
	
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	
	// @Scheduled(fixedRate = 5000)
	@Scheduled(cron="0 37 10 * * 3-5")
	public void reportCurrentTime() {
		Member m = memberService.findOne(126L);
		log.info("MEMBER :: " + m.getEmail());
		log.info("THE TIME IS NOW {}", dateFormat.format(new Date()));
	}
}
