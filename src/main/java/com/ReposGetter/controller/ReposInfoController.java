package com.ReposGetter.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ReposGetter.model.ReposInfo;
import com.ReposGetter.service.ReposInfoService;

@RestController
public class ReposInfoController {
	
	private final String info = "Type 'http://localhost:8080/{username}' to see his repositories names and amount of stars";
	
	@RequestMapping("/")
	public String getServiceInfo() {
		return info;
	}
	
	@GetMapping("/{name}")
	public ReposInfo getReposInfo(@PathVariable("name") String name) {
		return ReposInfoService.getReposInfo(name);
	}
}
