package com.logistics.expressmanagement.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.logistics.domain.*;
import com.logistics.expressmanagement.dao.ExpressManagementDao;

/**
 * DAO 实现
 * 
 * @author LW
 *
 */

public class ExpressManagementDaoImpl implements ExpressManagementDao {
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
	 * 根据ID查询预约单
	 */
	@Override
	public reservation getReservationInfoById(String reservation_id) {
		reservation reservationInfo = new reservation();
		Session session = getSession();
		String hql = "from reservation where reservation_id = :ID";
		Query query = session.createQuery(hql);
		query.setParameter("ID", reservation_id);
		reservationInfo = (reservation) query.uniqueResult();
		return reservationInfo;
	}

	/**
	 * 根据派送员ID查询派送员
	 */
	@Override
	public distributiontor getDistributorInfoById(String distributiontor_id) {
		distributiontor distributor = new distributiontor();
		Session session = getSession();
		String hql = "from distributiontor where distributiontor_id = :ID";
		Query query = session.createQuery(hql);
		query.setParameter("ID", distributiontor_id);
		distributor = (distributiontor) query.uniqueResult();
		return distributor;
	}

	/**
	 * 根据员工ID查询派送员
	 */
	@Override
	public distributiontor getDistributorInfoByBasicInfo(String staff_id) {
		distributiontor distributor = new distributiontor();
		Session session = getSession();
		String hql = "from distributiontor where distributiontor_basicinfo = :ID";
		Query query = session.createQuery(hql);
		query.setParameter("ID", staff_id);
		distributor = (distributiontor) query.uniqueResult();
		return distributor;
	}

	/**
	 * 根据派送员ID查询预约单
	 */
	@Override
	public reservation getReservationInfoByDistributorId(String distributiontor_id) {
		reservation reservationInfo = new reservation();
		Session session = getSession();
		String hql = "from reservation where reservation_distributiontor = :ID";
		Query query = session.createQuery(hql);
		query.setParameter("ID", distributiontor_id);
		reservationInfo = (reservation) query.uniqueResult();
		return reservationInfo;
	}

	/**
	 * 根据ID查询员工信息
	 */
	@Override
	public staff_basicinfo getStaffInfoById(String staff_superiorleader) {
		staff_basicinfo staffLeader = new staff_basicinfo();
		Session session = getSession();
		String hql = "from staff_basicinfo where staff_id = :ID";
		Query query = session.createQuery(hql);
		query.setParameter("ID", staff_superiorleader);
		staffLeader = (staff_basicinfo) query.uniqueResult();
		return staffLeader;
	}

	/**
	 * 根据ID查询单位信息
	 */
	@Override
	public unit getUnitInfoById(String staff_unit) {
		unit unitInfo = new unit();
		Session session = getSession();
		String hql = "from unit where unit_id = :ID";
		Query query = session.createQuery(hql);
		query.setParameter("ID", staff_unit);
		unitInfo = (unit) query.uniqueResult();
		return unitInfo;
	}

	/**
	 * 根据ID查询快件表
	 */
	@Override
	public express getExpressById(String express_id) {
		express expressInfo = new express();
		Session session = getSession();
		String hql = "from express where express_id = :ID";
		Query query = session.createQuery(hql);
		query.setParameter("ID", express_id);
		expressInfo = (express) query.uniqueResult();
		return expressInfo;
	}

	/**
	 * 获得路线信息（用于判断）
	 */
	@Override
	public String getExpressRouteInfoByExpressId(String express_id) {
		Session session = getSession();
		String hql = "select express_route_route_id from express_route where express_route_belongexpress =:ID order by --express_route_superior desc limit 1 ";
		Query query = session.createSQLQuery(hql);
		query.setParameter("ID", express_id);
		String expressRoute = (String) query.uniqueResult();
		return expressRoute;
	}

	/**
	 * 获得编号最大值
	 */
	@Override
	public String getMaxNumber(String hql) {
		Session session = getSession();
		Query query = session.createSQLQuery(hql);
		String number = (String) query.uniqueResult();
		return number;
	}

	/**
	 * 根据ID获得车辆信息
	 */
	@Override
	public vehicle getVehicleInfoById(String vehicle_id) {
		vehicle vehicleInfo = new vehicle();
		Session session = getSession();
		String hql = "from vehicle where vehicle_id = :ID ";
		Query query = session.createQuery(hql);
		query.setParameter("ID", vehicle_id);
		vehicleInfo = (vehicle) query.uniqueResult();
		return vehicleInfo;
	}

	/**
	 * 获得当前流转记录
	 */
	@Override
	public express_circulation getExpressCirculationInfoByExpressIdAndReceiver(String express_id, String staff_unit) {
		express_circulation expressCirculationInfo = new express_circulation();
		Session session = getSession();
		String hql = "from express_circulation where express_circulation_express_id = :expressId and express_circulation_receiver = :unitId  ";
		Query query = session.createQuery(hql);
		query.setParameter("expressId", express_id);
		query.setParameter("unitId", staff_unit);
		expressCirculationInfo = (express_circulation) query.uniqueResult();
		return expressCirculationInfo;
	}

	/**
	 * 根据ID查询快件详细信息
	 */
	@Override
	public expressinfo getExpressInfoById(String reservation_expressinfo) {
		expressinfo expressInfo = new expressinfo();
		Session session = getSession();
		String hql = "from expressinfo where expressinfo_id = :ID ";
		Query query = session.createQuery(hql);
		query.setParameter("ID", reservation_expressinfo);
		expressInfo = (expressinfo) query.uniqueResult();
		return expressInfo;
	}

	/**
	 * 根据ID查询用户信息
	 */
	@Override
	public userinfo getUserInfoById(String reservation_user) {
		userinfo userInfo = new userinfo();
		Session session = getSession();
		String hql = "from userinfo where userinfo_id = :ID ";
		Query query = session.createQuery(hql);
		query.setParameter("ID", reservation_user);
		userInfo = (userinfo) query.uniqueResult();
		return userInfo;
	}

	/**
	 * 获得职位
	 */
	@Override
	public position getPositionById(String staff_position) {
		position staffPosition = new position();
		Session session = getSession();
		String hql = "from position where position_id = :ID ";
		Query query = session.createQuery(hql);
		query.setParameter("ID", staff_position);
		staffPosition = (position) query.uniqueResult();
		return staffPosition;
	}

	/**
	 * 根据配送员ID查询派送表信息
	 */
	@Override
	public express_send getExpressSendInfoByDistributorId(String distributiontor_id) {
		express_send expressSendInfo = new express_send();
		Session session = getSession();
		String hql = "from express_send where express_send_distributiontor = :ID ";
		Query query = session.createQuery(hql);
		query.setParameter("ID", distributiontor_id);
		expressSendInfo = (express_send) query.uniqueResult();
		return expressSendInfo;
	}

	/**
	 * 根据员工ID查询驾驶员表信息
	 */
	@Override
	public driver getDriverInfoByBasicInfo(String staff_id) {
		driver driverInfo = new driver();
		Session session = getSession();
		String hql = "from driver where driver_basicinfoid = :ID ";
		Query query = session.createQuery(hql);
		query.setParameter("ID", staff_id);
		driverInfo = (driver) query.uniqueResult();
		return driverInfo;
	}

	/**
	 * 根据车辆ID查询车辆-快件关联表信息
	 */
	@Override
	public vehicle_express_relevance getVehicleExpressRelevanceByVehicleId(String driver_vehicle) {
		vehicle_express_relevance vehicleExpressRelevance = new vehicle_express_relevance();
		Session session = getSession();
		String hql = "from vehicle_express_relevance where vehicle_express_relevance_vehicleinfo = :ID ";
		Query query = session.createQuery(hql);
		query.setParameter("ID", driver_vehicle);
		vehicleExpressRelevance = (vehicle_express_relevance) query.uniqueResult();
		return vehicleExpressRelevance;
	}

	/**
	 * 根据快件ID查询流转表信息
	 */
	@Override
	public express_circulation getExpressCirculationInfoByExpressId(String express_id) {
		express_circulation expressCirculation = new express_circulation();
		Session session = getSession();
		String hql = "from express_circulation where express_circulation_express_id = :ID ";
		Query query = session.createQuery(hql);
		query.setParameter("ID", express_id);
		expressCirculation = (express_circulation) query.uniqueResult();
		return expressCirculation;
	}

	/**
	 * 根据ID查路线
	 */
	@Override
	public route getRouteInfoById(String express_route_route_id) {
		route routeInfo = new route();
		Session session = getSession();
		String hql = "from route where route_id = :ID ";
		Query query = session.createQuery(hql);
		query.setParameter("ID", express_route_route_id);
		routeInfo = (route) query.uniqueResult();
		return routeInfo;
	}

	/**
	 * 根据快件ID查询流转信息
	 */
	@Override
	public vehicle_express_relevance getVehicleExpressRelevanceByExpressId(String express_id) {
		vehicle_express_relevance vehicleExpressRelevance = new vehicle_express_relevance();
		Session session = getSession();
		String hql = "from vehicle_express_relevance where vehicle_express_relevance_expressinfo = :ID ";
		Query query = session.createQuery(hql);
		query.setParameter("ID", express_id);
		vehicleExpressRelevance = (vehicle_express_relevance) query.uniqueResult();
		return vehicleExpressRelevance;
	}

}
