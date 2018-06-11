package com.logistics.loginregister.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.logistics.domain.position;
import com.logistics.domain.staff_basicinfo;
import com.logistics.domain.userinfo;
import com.logistics.loginregister.dao.LoginRegisterDao;

/**
 * 注册登陆DAO实现层
 * 
 * @author LW
 *
 */
public class LoginRegisterDaoImpl implements LoginRegisterDao {
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
	 * 用户登陆判断
	 */
	@Override
	public userinfo loginByUser(String username, String password) {
		Session session = getSession();
		String hql = "from userinfo where userinfo_phonenumber='" + username + "" + "'and userinfo_password ='"
				+ password + "'";
		System.out.println("____________" + hql);
		Query query = session.createQuery(hql);
		List<userinfo> listuserinfo = query.list();
		if (listuserinfo.get(0).getUserinfo_password() != null
				&& listuserinfo.get(0).getUserinfo_phonenumber() != null) {
			System.out.println(listuserinfo.get(0).getUserinfo_password());
			System.out.println(password);
			System.out.println(username);
			System.out.println(listuserinfo.get(0).getUserinfo_phonenumber());
			System.out.println("tttttttttttttttttttttttttttttt");
			if (listuserinfo.get(0).getUserinfo_password().equals(password)
					&& listuserinfo.get(0).getUserinfo_phonenumber().equals(username)) {
				System.out.println("hhhhhhhhhhhhhhhhhhhhhh");
				return listuserinfo.get(0);
			}
		}
		return null;

	}

	/**
	 * 员工登陆判断
	 */
	@Override
	public staff_basicinfo loginByStaff(String username, String password) {
		Session session = getSession();
		String hql = "from staff_basicinfo where staff_num='" + username + "" + "'and staff_password ='" + password
				+ "'";
		Query query = session.createQuery(hql);
		List<staff_basicinfo> liststaff_basicinfo = query.list();

		if (liststaff_basicinfo != null && !liststaff_basicinfo.isEmpty()) {
			if (liststaff_basicinfo.get(0).getStaff_num() != null
					&& liststaff_basicinfo.get(0).getStaff_password() != null) {
				if (liststaff_basicinfo.get(0).getStaff_password().equals(password)
						&& liststaff_basicinfo.get(0).getStaff_num().equals(username)) {
					return liststaff_basicinfo.get(0);
				}
			}

		}

		return null;
	}
	/**
	 * 查找员工表是否有此人
	 */
}
