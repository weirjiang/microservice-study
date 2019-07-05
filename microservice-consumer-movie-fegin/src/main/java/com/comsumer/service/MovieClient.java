package com.comsumer.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.comsumer.entity.User;

@FeignClient("microservice-provider-user")
public interface MovieClient {
	  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
	  public User findById(@PathVariable("id") Long id);
}
