package com.logistics.personnelmanagement.service.impl;

import com.logistics.personnelmanagement.dao.PersonnelManagementDao;
import com.logistics.personnelmanagement.service.PersonnelManagementService;

public class PersonnelManagementServiceImpl implements PersonnelManagementService {
	/**
	 * 注入DAO层
	 */
private PersonnelManagementDao personnelManagementDao;

public void setPersonnelManagementDao(PersonnelManagementDao personnelManagementDao) {
	this.personnelManagementDao = personnelManagementDao;
}

}
