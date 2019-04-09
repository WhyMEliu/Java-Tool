package com.peng.monitor_tuning.matanalyze;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemoryController {
	
	private List<User> userList = new ArrayList<>();
	private List<Class<?>> classList = new ArrayList<>();
	
	/**
	 * -Xmx32m -Xms32m
	 */
	@GetMapping("/heap")
	public String heap(){
		int i = 0;
		while(true){
			userList.add(new User(i++,UUID.randomUUID().toString()));
		}
	}
	
	/**
	 * -XX:MetaspaceSize=32m -XX:MaxMetaspaceSize=32m
	 */
	@GetMapping("/nonheap")
	public String nonheap(){
		while(true){
			classList.addAll(Metaspace.createClasses());
		}
	}
	
}
