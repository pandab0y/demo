package com.example.demo.service.db1;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;

import com.example.demo.entity.User;

public interface UserService {
	
	@Cacheable(value="random")
	User getUserById(int id);
	
	List<User> getUserList();
	
	int getUserToatl();

	String getWeibo(String url, String id, int page, String cookie);
	
	void saveUser(String username, String password);
}
