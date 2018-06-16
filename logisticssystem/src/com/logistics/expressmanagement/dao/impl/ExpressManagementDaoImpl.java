package com.logistics.expressmanagement.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.logistics.domain.distributiontor;
import com.logistics.domain.express;
import com.logistics.domain.express_circulation;
import com.logistics.domain.express_route;
import com.logistics.domain.expressinfo;
import com.logistics.domain.reservation;
import com.logistics.domain.staff_basicinfo;
import com.logistics.domain.unit;
import com.logistics.domain.userinfo;
import com.logistics.domain.vehicle;
import com.logistics.expressmanagement.dao.ExpressManagementDao;
/**
 * DAO 实现
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
	public express_route getExpressRouteInfoByExpressId(String express_id) {
		express_route expressRouteInfo = new express_route();
		Session session = getSession();
		String hql = "from express_route where express_route_belongexpress = :ID and express_route_state = '未完成' order by --express_route_superior limit 1 ";
		Query query = session.createQuery(hql);
		query.setParameter("ID", express_id);
		expressRouteInfo = (express_route) query.uniqueResult();
		return expressRouteInfo;
	}

	/**
	 * 获得编号最大值
	 */
	@Override
	public String getMaxNumber(String hql) {
		Session session = getSession();
		Query query = session.createQuery(hql);
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

}
