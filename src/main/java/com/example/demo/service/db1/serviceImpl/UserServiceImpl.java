package com.example.demo.service.db1.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.demo.common.utils.HttpUtil;
import com.example.demo.dao.userMapper.UserMapper;
import com.example.demo.entity.SysUser;
import com.example.demo.entity.User;
import com.example.demo.service.db1.UserService;

@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
	@Autowired
    private UserMapper userMapper;
	
	@Override
	public User getUserById(int id) {
		System.out.println("第一次缓存");
		return userMapper.getUserById(id);
	}

	@Override
	public List<User> getUserList() {
		
		return userMapper.selectList(new EntityWrapper<User>().orderBy("user_id"));
	}

	@Override
	public int getUserToatl() {
		// TODO Auto-generated method stub
		return userMapper.selectCount(new EntityWrapper<User>().or());
	}

	@Override
	public String getWeibo(String url, String id, int page, String cookie) {
		JSONObject body = new JSONObject();
		body.put("id", id);
		String result = HttpUtil.httpRequest(url, HttpMethod.GET, body.toString(), cookie);
		return result;
	}

	@Override
	public void saveUser(String username, String password) {
		SysUser user = new SysUser();
		user.setUsername(username);
		user.setPassword(new BCryptPasswordEncoder().encode(password));
		userMapper.insert(user);
	}

	
}
