package com.logistics.test.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.logistics.domain.Testdomain;
import com.logistics.test.dao.TestDao;
import com.logistics.test.service.TestService;

/**
 * serviceimpl
 * 
 * @author 哈哈哈哈哈哈
 *
 */
public class TestServiceImpl implements TestService {
	/**
	 * dao层注入
	 */
	private TestDao testDao;

	public void setTestDao(TestDao testDao) {
		this.testDao = testDao;
	}

	@Override
	public void Add(Testdomain w) {
		System.out.println("########:" + w);
		testDao.saveOrUpdateObject(w);

	}

	/**
	 * 查询Testdomain表里的所有集合 return 返回listw是Testdomain的所有信息
	 */
	@Override
	public List<Testdomain> select() {
		// list
		List<Testdomain> listw = new ArrayList<>();
		listw = (List<Testdomain>) testDao.listObject("from Testdomain");

		return listw;
	}

}
