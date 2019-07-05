package com.comsumer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.comsumer.entity.User;

import feign.hystrix.FallbackFactory;

@FeignClient(name = "microservice-provider-user", fallbackFactory = MovieClientCallbackFactory.class)
public interface MovieClient {
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public User findById(@PathVariable("id") Long id);

}
@Component
class MovieClientCallbackFactory implements FallbackFactory<MovieClient> {
	private static final Logger LOGGER = LoggerFactory.getLogger(MovieClientCallbackFactory.class);

	@Override
	public MovieClient create(Throwable cause) {
		// TODO Auto-generated method stub
		MovieClientCallbackFactory.LOGGER.info("fallback; reason was:", cause);
		return new MovieClient() {
			@Override
			public User findById(Long id) {
				// TODO Auto-generated method stub
				User user = new User();
				user.setId(-1L);
				user.setUsername("默认用户");
				return user;
			}
		};
	}
}