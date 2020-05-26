package com.example.demo.dao.userMapper;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.example.demo.entity.SysUser;
import com.example.demo.entity.User;

public interface UserMapper extends BaseMapper<User>{
	
    User getUserById(int id);

    SysUser findByUserName(String userName);

	void insert(SysUser user);

}
