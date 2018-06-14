package com.logistics.expressmanagementW.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.logistics.domain.express;
import com.logistics.domain.express_route;
import com.logistics.domain.expressinfo;
import com.logistics.domain.position;
import com.logistics.domain.route;
import com.logistics.domain.vehicle;
import com.logistics.expressmanagementW.dao.ExpressManagementDao2;

/**
 * DAO 实现
 * 
 * @author LW
 *
 */

public class ExpressManagementDaoImpl2 implements ExpressManagementDao2 {
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
	 * 根据快件ID获取快件信息
	 */
	@Override
	public express getExpress(String express_id) {
		express expressNew = new express();
		Session session = getSession();
		String hql = " from express where express_id = :ID";
		Query query = session.createQuery(hql);
		query.setParameter("ID", express_id);
		expressNew = (express) query.uniqueResult();
		if (expressNew != null) {
			return expressNew;
		}
		return null;
	}

	/**
	 * 根据车辆ID获取车辆状态
	 */
	@Override
	public vehicle getVehicle(String vehicle_id) {
		vehicle vehicleNew = new vehicle();
		Session session = getSession();
		String hql = " from vehicle where vehicle_id = :ID";
		Query query = session.createQuery(hql);
		query.setParameter("ID", vehicle_id);
		vehicleNew = (vehicle) query.uniqueResult();
		if (vehicleNew != null) {
			return vehicleNew;
		}
		return null;
	}

	/**
	 * 根据快件ID获取快件信息ID
	 */
	@Override
	public expressinfo getExpressInfo(String express_expressinfoid) {
		expressinfo expressinfoNew = new expressinfo();
		Session session = getSession();
		String hql = " from expressinfo where expressinfo_id = :ID";
		Query query = session.createQuery(hql);
		query.setParameter("ID", express_expressinfoid);
		expressinfoNew = (expressinfo) query.uniqueResult();
		if (expressinfoNew != null) {
			return expressinfoNew;
		}
		return null;
	}

	/**
	 * 根据快件Id查询发往下一站的路线ID
	 */
	@Override
	public express_route getexpressRoute(String express_id) {
		express_route expressRoute = new express_route();
		Session session = getSession();
		String hql = " from express_route where express_route_belongexpress = :ID and express_route_state = '未完成' order by express_route_superior limit 1 ";
		Query query = session.createQuery(hql);
		query.setParameter("ID", express_id);
		expressRoute = (express_route) query.uniqueResult();
		if (expressRoute != null) {
			return expressRoute;
		}
		return null;
	}

	@Override
	public route getRoute(String express_route_id) {
		route routeNew = new route();
		Session session = getSession();
		String hql = " from route where route_id = :ID";
		Query query = session.createQuery(hql);
		query.setParameter("ID", express_route_id);
		routeNew = (route) query.uniqueResult();
		if (routeNew != null) {
			return routeNew;
		}
		return null;
	}

}
