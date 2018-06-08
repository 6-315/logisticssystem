package com.logistics.personnelmanagement.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.logistics.domain.staff_basicinfo;
import com.logistics.personnelmanagement.dao.PersonnelManagementDao;

/**
 * 人事管理DAO实现层
 * 
 * @author LW
 *
 */
public class PersonnelManagementDaoImpl implements PersonnelManagementDao {
	/**
	 * session注入
	 */
	private SessionFactory sessionFactory;

	public Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * session注入结束
	 */

	/**
	 * 保存、更新对象
	 * 
	 * @author JXX
	 * @date 2018/04/12
	 * @param obj
	 * @modify JXX 2018/04/12
	 */
	@Override
	public void saveOrUpdateObject(Object obj) {
		Session session = getSession();
		session.saveOrUpdate(obj);
		session.flush();
	}

	/**
	 * 分页获取对象，这里是获取一页中的数据
	 * 
	 * @param hql
	 * @param offset
	 *            当前页
	 * @param length
	 *            获取每页记录数
	 * @return
	 */
	@Override
	public List<?> queryForPage(String hql, int offset, int length) {
		Session session = getSession();
		Query query = session.createQuery(hql);
		query.setFirstResult((offset - 1) * length);
		query.setMaxResults(length);
		List<?> list = query.list();
		session.clear();
		return list;
	}

	/**
	 * 获取对象总数量
	 * 
	 * @param hql
	 * @return
	 */
	@Override
	public int getCount(String hql) {
		Session session = getSession();
		Query query = session.createQuery(hql);
		if (query.list().size() > 0) {
			return Integer.parseInt(query.list().get(0).toString());
		} else {
			return 0;
		}
	}

	/**
	 * 移除对象
	 */
	@Override
	public int removeObject(Object obj) {
		getSession().delete(obj);
		return 1;
	}

	/**
	 * 获取对象列表
	 */
	@Override
	public List<?> listObject(String hql) {
		Session session = getSession();
		Query query = session.createQuery(hql);
		List<?> list = query.list();
		session.clear();
		return list;
	}
	/**
	 * 查找员工表是否有此人
	 */
	@Override
	public staff_basicinfo getstaffById(String id) {
		staff_basicinfo staffBasicInfo = new staff_basicinfo();
		Session session = getSession();
		String hql = "from staff_basicinfo where staff_id = :ID";
		Query query = session.createQuery(hql);
		query.setParameter("ID", id);
		staffBasicInfo = (staff_basicinfo) query.uniqueResult();
		System.out.println("OK");
		return staffBasicInfo;
	}

	/**
	 * 查找员工表是否有此人
	 */
	@Override
	public staff_basicinfo getstaffBasicinfo(String staff_id) {
		staff_basicinfo staffBasicInfo = new staff_basicinfo();
		Session session = getSession();
		String hql = "from staff_basicinfo where staff_id = :ID";
		Query query = session.createQuery(hql);
		query.setParameter("ID", staff_id);
		staffBasicInfo = (staff_basicinfo) query.uniqueResult();
		System.out.println("OK");
		return staffBasicInfo;
	}


}
