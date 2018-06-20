package com.logistics.userinfo.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.logistics.domain.address;
import com.logistics.domain.expressinfo;
import com.logistics.domain.position;
import com.logistics.domain.userinfo;
import com.logistics.userinfo.dao.UserinfoDao;

/**
 * 用户信息DAO 实现层
 * 
 * @author LW
 *
 */
public class UserInfoDaoImpl implements UserinfoDao {
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
		System.out.println("oooooo" + hql);
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
	 * 根据user infoID获取用户信息
	 */
	@Override
	public userinfo getUserInfo(String userinfo_id) {
		userinfo userinfoNew = new userinfo();
		Session session = getSession();
		String hql = " from userinfo where userinfo_id = :ID";
		Query query = session.createQuery(hql);
		query.setParameter("ID", userinfo_id);
		userinfoNew = (userinfo) query.uniqueResult();
		if (userinfoNew != null) {

			return userinfoNew;
		}
		return null;
	}

	/**
	 * 根据快件 的ID获取快件的信息
	 */
	@Override
	public expressinfo getExpressInfoById(String express_id) {
		expressinfo expressInfo = new expressinfo();
		Session session = getSession();
		String hql = " from expressinfo where expressinfo_id = :ID";
		Query query = session.createQuery(hql);
		query.setParameter("ID", express_id);
		expressInfo = (expressinfo) query.uniqueResult();
		if (expressInfo != null) {
			return expressInfo;
		}
		return null;
	}

	/**
	 * 查询地址表里有没有默认地址
	 */
	@Override
	public address getAddressByState() {
		address addressNew = new address();
		Session session = getSession();
		String hql = " from address where address_isdefault = '是'";
		Query query = session.createQuery(hql);
		addressNew = (address) query.uniqueResult();
		if (addressNew != null) {
			return addressNew;
		}
		return null;
	}

}
