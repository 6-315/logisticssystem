package com.logistics.expressmanagementW.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.logistics.domain.distributiontor;
import com.logistics.domain.express;
import com.logistics.domain.express_route;
import com.logistics.domain.express_send;
import com.logistics.domain.expressinfo;
import com.logistics.domain.route;
import com.logistics.domain.team;
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
		System.out.println("ooooooo");
		expressinfo expressinfoNew = new expressinfo();
		Session session = getSession();
		String hql = " from expressinfo where expressinfo_id = :ID";
		Query query = session.createQuery(hql);
		query.setParameter("ID", express_expressinfoid);
		expressinfoNew = (expressinfo) query.uniqueResult();
		System.out.println("uuuuuuuuuuuuuu" + express_expressinfoid);
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
		String hql = "from express_route where express_route_belongexpress = :ID and express_route_state = '未完成' order by express_route_superior";
		System.out.println("hql:" + hql);
		Query query = session.createQuery(hql);
		query.setParameter("ID", express_id);
		query.setFirstResult(0);
		query.setMaxResults(1);
		List<express_route> listRoute = new ArrayList<>();
		listRoute = query.list();
		if (listRoute.size() > 0) {
			System.out.println("UUUUUUUU" + listRoute);
			return listRoute.get(0);
		} else {
			return null;
		}
		/*
		 * expressRoute = (express_route) query.uniqueResult();
		 * System.out.println("UUUUUUUUUUUU:" + expressRoute); if (expressRoute != null)
		 * { return expressRoute; }
		 */
	}

	/**
	 * 获取路线
	 */
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

	/**
	 * 根据员工的ID查询配送员
	 */
	@Override
	public distributiontor getDistributiontor(String staff_id) {
		distributiontor distributiontorNew = new distributiontor();
		Session session = getSession();
		String hql = " from distributiontor where distributiontor_id = :ID";
		Query query = session.createQuery(hql);
		query.setParameter("ID", staff_id);
		distributiontorNew = (distributiontor) query.uniqueResult();
		if (distributiontorNew != null) {
			return distributiontorNew;
		}
		return null;
	}

	/**
	 * 根据快件ID获取快件配送 表记录
	 */
	@Override
	public express_send getExpressSend(String express_id) {
		express_send expressSend = new express_send();
		Session session = getSession();
		String hql = " from express_send where express_send_express_id = :ID";
		Query query = session.createQuery(hql);
		query.setParameter("ID", express_id);
		expressSend = (express_send) query.uniqueResult();
		if (expressSend != null) {
			return expressSend;
		}

		return null;
	}

	/**
	 * 根据路线ID获取车队
	 */
	@Override
	public team getTeam(String route_id) {
		team teamNew = new team();
		Session session = getSession();
		String hql = " from team where team_route = :ID";
		Query query = session.createQuery(hql);
		query.setParameter("ID", route_id);
		teamNew = (team) query.uniqueResult();
		if (teamNew != null) {
			return teamNew;
		}
		return null;
	}
}
