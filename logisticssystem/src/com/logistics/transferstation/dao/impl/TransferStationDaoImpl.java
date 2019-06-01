package com.logistics.transferstation.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.logistics.domain.driver;
import com.logistics.domain.position;
import com.logistics.domain.staff_basicinfo;
import com.logistics.domain.team;
import com.logistics.domain.unit;
import com.logistics.domain.vehicle;
import com.logistics.transferstation.dao.TransferStationDao;

/**
 * 中转站管理的DAO实现层
 * 
 * @author LW
 *
 */
public class TransferStationDaoImpl implements TransferStationDao {
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
	 * 根据ID查询中转站
	 */
	public unit getTransferStationInfoById(String trim) {
		unit transferStation = new unit();
		Session session = getSession();
		String hql = "from unit where unit_id = :ID ";
		Query query = session.createQuery(hql);
		query.setParameter("ID", trim);
		transferStation = (unit) query.uniqueResult();

		return transferStation;

	}

	/**
	 * 根据ID查询staff_basicinfo表中的信息
	 */
	@Override
	public staff_basicinfo getBasicinfoById(String trim) {
		staff_basicinfo staff_basicinfo = new staff_basicinfo();
		Session session = getSession();
		String hql = "from staff_basicinfo where staff_id = :ID";
		Query query = session.createQuery(hql);
		query.setParameter("ID", trim);
		staff_basicinfo = (staff_basicinfo) query.uniqueResult();
		return staff_basicinfo;
	}

	/**
	 * 按照倒序查中转站编号最大值
	 */
	@Override
	public String getTransferStationByNum(String unit_num) {
		Session session = getSession();
		String hql = "select substring(unit_num,2) from unit where unit_type=:num  order by --substring(unit_num, 2) desc limit 1";
		Query query = session.createSQLQuery(hql);
		query.setParameter("num", "中转站");
		String maxNum = (String) query.uniqueResult();
		return maxNum;
	}
/**
 * 查询配送点的最大编号
 */
	@Override
	public String getDistributionByNum(String unit_num) {
		Session session = getSession();
		String hql = "select substring(unit_num,5) from unit where unit_type=:num  order by --substring(unit_num, 5) desc limit 1";
		Query query = session.createSQLQuery(hql);
		query.setParameter("num", "配送点");
		String maxNum = (String) query.uniqueResult();
		return maxNum;
	}

	/**
	 * 根据ID查询车队表中的信息
	 */
	@Override
	public team getTeamById(String trim) {
		team team = new team();
		Session session = getSession();
		String hql = "from team where team_id = :ID";
		Query query = session.createQuery(hql);
		query.setParameter("ID", trim);
		team = (team) query.uniqueResult();
		return team;
	}

	/**
	 * 根据ID查询车队表中的信息
	 */
	@Override
	public vehicle getVehicleById(String trim) {
		vehicle vehicle = new vehicle();
		Session session = getSession();
		String hql = "from vehicle where vehicle_id = :ID";
		Query query = session.createQuery(hql);
		query.setParameter("ID", trim);
		vehicle = (vehicle) query.uniqueResult();
		return vehicle;
	}

	/**
	 * 根据ID查询员工表中的信息
	 */
	@Override
	public driver getDriverById(String trim) {
		driver driver = new driver();
		Session session = getSession();
		String hql = "from driver where driver_id = :ID";
		Query query = session.createQuery(hql);
		query.setParameter("ID", trim);
		driver = (driver) query.uniqueResult();
		return driver;
	}

	/**
	 * 根据id获取职位
	 */
	@Override
	public position getPositionById(String trim) {
		position position = new position();
		Session session = getSession();
		String hql = "from position where position_id = :ID";
		Query query = session.createQuery(hql);
		query.setParameter("ID", trim);
		position = (position) query.uniqueResult();
		if (position != null) {
			return position;
		}
		return null;
	}
	@Override
	public team getTeamByLeader(String trim) {
		team team = new team();
		Session session = getSession();
		String hql = "from team where team_leader = :ID";
		Query query = session.createQuery(hql);
		query.setParameter("ID", trim);
		team = (team) query.uniqueResult();
		return team;
	}
	@Override
	public driver getDriverByVehicle_id(String trim) {
		driver driver = new driver();
		Session session = getSession();
		String hql = "from driver where driver_vehicle= :ID";
		Query query = session.createQuery(hql);
		query.setParameter("ID", trim);
		driver = (driver) query.uniqueResult();
		return driver;
	}
}
