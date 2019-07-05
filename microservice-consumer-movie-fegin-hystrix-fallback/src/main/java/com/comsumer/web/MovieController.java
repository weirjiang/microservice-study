package com.comsumer.web;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.comsumer.entity.User;
import com.comsumer.service.MovieClient;

@RestController
public class MovieController {
	  private static final Logger LOGGER = LoggerFactory.getLogger(MovieController.class);
	  @Autowired
	  MovieClient movieClient;
	@GetMapping("/user/{id}")
	public User findById(@PathVariable Long id) {
		return movieClient.findById(id);
	}

	
	@GetMapping("test")
	public String  test() {
		return "test";

	}
}
