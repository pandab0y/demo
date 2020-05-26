package com.example.demo.service.db2.serviceImpl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.demo.dao.unionMapper.UnionMapper;
import com.example.demo.entity.Company;
import com.example.demo.service.db2.UnionService;

@Service("unionService")
public class UnionServiceImpl extends ServiceImpl<UnionMapper, Company> implements UnionService {

	@Autowired
	private UnionMapper unionMapper;
	@Override
	public Company getCompanyInfo(int id) {
		
		return unionMapper.selectById(id);
	}
	@Override
	public List<Company> getCompanyList() {
		
		return  unionMapper.selectPage(new RowBounds(0,10), new EntityWrapper<Company>().orderBy("id"));
	}

}
