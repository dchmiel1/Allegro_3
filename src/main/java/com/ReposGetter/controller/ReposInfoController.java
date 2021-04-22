package com.ReposGetter.controller;

import java.util.ArrayList;
import java.util.Arrays;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ReposGetter.model.ReposInfo;

@RestController
public class ReposInfoController {
	
	private final String INFO = "Type 'http://localhost:8080/{username}' to see his repositories and amount of stars'";
	
	@RequestMapping("/")
	public String getServiceInfo() {
		return INFO;
	}
	
	@GetMapping("/{name}")
	public ReposInfo getReposInfo(@PathVariable("name") String name) {
		return new ReposInfo(0, new ArrayList<>(Arrays.asList(name, "test", "123")));
	}
}
