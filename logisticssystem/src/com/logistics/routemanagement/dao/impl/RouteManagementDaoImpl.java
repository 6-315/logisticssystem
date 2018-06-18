package com.logistics.routemanagement.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.logistics.domain.route;
import com.logistics.domain.staff_basicinfo;
import com.logistics.domain.unit;
import com.logistics.routemanagement.dao.RouteManagementDao;

/**
 * 路线管理DAO实现层
 * 
 * @author LW
 *
 */
public class RouteManagementDaoImpl implements RouteManagementDao {
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
	 * 根据ID查单条信息
	 */
	@Override
	public route getRouteById(String route_id) {
		route rout = new route();
		Session session = getSession();
		String sql = "from route where route_id=:ID";
		Query query = session.createQuery(sql);
		query.setParameter("ID", route_id);
		rout = (route) query.uniqueResult();
		return rout;
	}

	/**
	 * 获取staff_basicinfo单个对象
	 */
	@Override
	public staff_basicinfo getStaff_Basicinfo(String sql) {
		Session session = getSession();
		Query query = session.createQuery(sql);
		List<staff_basicinfo> listStaff_Basicinfo = query.list();
		if (listStaff_Basicinfo != null && !listStaff_Basicinfo.isEmpty()) {
			return listStaff_Basicinfo.get(0);
		}
		return null;

	}
/**
 * 获取unit表单条数据
 */
	@Override
	public unit getRoute_Departurestation(String sql1) {
		Session session = getSession();
		Query query = session.createQuery(sql1);
		List<unit> listUnit = query.list();
		if (listUnit != null && !listUnit.isEmpty()) {
			return listUnit.get(0);
		}
		return null;
	}

	@Override
	public unit getRoute_Terminalstation(String sql2) {
		Session session = getSession();
		Query query = session.createQuery(sql2);
		List<unit> listUnit = query.list();
		if (listUnit != null && !listUnit.isEmpty()) {
			return listUnit.get(0);
		}
		return null;
	}
/**
 * 获取最后一个route_num
 */
	@Override
	public String getRoutNum(String sql) {
		sql="SELECT MAX(route_num) FROM route";
		return "routeNum";
	}

}
