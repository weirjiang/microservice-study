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

@RestController
public class MovieController {
	  private static final Logger LOGGER = LoggerFactory.getLogger(MovieController.class);
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private LoadBalancerClient loadBalancerClient;

	@GetMapping("/user/{id}")
	public User findById(@PathVariable Long id) {
		ServiceInstance serviceInstance = loadBalancerClient.choose("microservice-provider-user");
        String url = "http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort() + "/"+id;
//		return this.restTemplate.getForObject("http://microservice-provider-user/" + id, User.class);
		return this.restTemplate.getForObject(url, User.class);

	}

	@GetMapping("/log-user-instance")
	public void logUserInstance() {
		ServiceInstance serviceInstance = this.loadBalancerClient.choose("microservice-provider-user");
		// 打印当前选择的是哪个节点
		MovieController.LOGGER.info("{}:{}:{}", serviceInstance.getServiceId(), serviceInstance.getHost(), serviceInstance.getPort());
	}
	
	@GetMapping("test")
	public String  test() {
		return "test";

	}
}
