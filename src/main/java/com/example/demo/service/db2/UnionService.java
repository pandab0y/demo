package com.example.demo.service.db2;

import java.util.List;

import com.example.demo.entity.Company;

public interface UnionService {
	
	List<Company> getCompanyList();

	Company getCompanyInfo(int id);
}
