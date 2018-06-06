package com.logistics.test.service;

import java.util.List;

import com.logistics.domain.Testdomain;

public interface TestService {

	void Add(Testdomain w);

	List<Testdomain> select();


}
