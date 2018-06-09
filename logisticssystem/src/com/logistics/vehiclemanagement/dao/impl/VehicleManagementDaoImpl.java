package com.logistics.vehiclemanagement.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.logistics.domain.staff_basicinfo;
import com.logistics.domain.team;
import com.logistics.domain.unit;
import com.logistics.domain.vehicle;
import com.logistics.vehiclemanagement.dao.VehicleManagementDao;

/**
 * 车辆管理DAO实现层
 *  
 * @author LW
 *
 */
public class VehicleManagementDaoImpl implements VehicleManagementDao {
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
	 * 根据ID查询车辆信息
	 */
	@Override
	public vehicle getVehicleInfoById(String vehicleId) {
		vehicle vehicleInfo = new vehicle();
		Session session = getSession();
		String hql = "from vehicle where vehicle_id = :ID";
		Query query = session.createQuery(hql);
		query.setParameter("ID", vehicleId);
		vehicleInfo = (vehicle) query.uniqueResult();
		return vehicleInfo;
	}

	/**
	 * 根据ID查询员工信息
	 */
	@Override
	public staff_basicinfo getStaffInfoById(String vehicle_acquisitionpeople) {
		staff_basicinfo staff_BasicInfo = new staff_basicinfo();
		Session session = getSession();
		String hql = "from staff_basicinfo where staff_id = :ID";
		Query query = session.createQuery(hql);
		query.setParameter("ID", vehicle_acquisitionpeople);
		staff_BasicInfo = (staff_basicinfo) query.uniqueResult();
		return staff_BasicInfo;
	}

	/**
	 * 根据ID查询单位信息
	 */
	@Override
	public unit getUnitInfoById(String vehicle_unit) {
		unit unitInfo = new unit();
		Session session = getSession();
		String hql = "from unit where unit_id = :ID";
		Query query = session.createQuery(hql);
		query.setParameter("ID", vehicle_unit);
		unitInfo = (unit) query.uniqueResult();
		return unitInfo;
	}


	/**
	 * 根据ID查询车队信息
	 */
	@Override
	public team getTeamInfoById(String vehicle_team) {
		team vehicleTeam = new team();
		Session session = getSession();
		String hql = "from team where team_id = :ID";
		Query query = session.createQuery(hql);
		query.setParameter("ID", vehicle_team);
		vehicleTeam = (team) query.uniqueResult();
		return vehicleTeam;
	}

	/**
	 * 根据车牌号查询信息
	 */
	@Override
	public vehicle getVehicleInfoByPlateNumber(String vehicle_platenum) {
		vehicle vehicleInfo = new vehicle();
		Session session = getSession();
		String hql = "from vehicle where vehicle_platenum = :ID";
		Query query = session.createQuery(hql);
		query.setParameter("ID", vehicle_platenum);
		vehicleInfo = (vehicle) query.uniqueResult();
		return vehicleInfo;
	}

}
