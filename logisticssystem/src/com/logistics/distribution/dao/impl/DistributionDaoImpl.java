package com.logistics.distribution.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.logistics.distribution.dao.DistributionDao;
import com.logistics.domain.team;
import com.logistics.domain.unit;
/**
 * 配送点管理的DAO实现层
 * @author LW
 *
 */
public class DistributionDaoImpl implements DistributionDao {
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

	@Override
	public unit getDistributionInfoById(String trim ) {
		unit unit = new unit();
		Session session = getSession();
		String hql = "from unit where unit_id = :ID";
		Query query = session.createQuery(hql);
		query.setParameter("ID", trim);
		unit = (unit) query.uniqueResult();
		return unit;
	}

	@Override
	public String getDistributionByNum(String unit_num) {
		Session session = getSession();
		String hql = "select substring(unit_num,5) from unit where unit_type=:num  order by --substring(unit_num, 5) desc limit 1";
		System.out.println(hql);
		Query query = session.createSQLQuery(hql);
		query.setParameter("num", "配送点");
		String maxNum = (String) query.uniqueResult();
		return maxNum;
	}

}
