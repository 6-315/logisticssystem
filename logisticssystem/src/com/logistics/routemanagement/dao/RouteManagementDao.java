package com.logistics.routemanagement.dao;

import java.util.List;

import com.logistics.domain.route;
import com.logistics.domain.staff_basicinfo;
import com.logistics.domain.unit;

/**
 * 路线管理dao层接口
 * 
 * @author LW
 *
 */
public interface RouteManagementDao {

	/**
	 * 保存、更新对象
	 * 
	 * @author JXX
	 * @date 2018/04/12
	 * @param obj
	 * @modify JXX 2018/04/12
	 */
	public void saveOrUpdateObject(Object obj);

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
	public List<?> queryForPage(String hql, int offset, int length);

	/**
	 * 获取对象总数量
	 * 
	 * @param hql
	 * @return
	 */
	public int getCount(String hql);

	/**
	 * 删除对象记录
	 * 
	 * @param obj
	 */
	public int removeObject(Object obj);

	/**
	 * 获取对象列表
	 */
	public List<?> listObject(String hql);

	/**
	 * 
	 * @param trim
	 * @return
	 */

	/**
	 * 获取staff_basicinfo单条数据
	 * 
	 * @return
	 */

	public staff_basicinfo getStaff_Basicinfo(String sql);
	/**
	 * 获取unit单条数据
	 * 
	 * @return
	 */

	public unit getRoute_Departurestation(String sql1);

	public unit getRoute_Terminalstation(String sql2);
/**
 * 根据route_id查route表单条信息
 * @param route_id
 * @return
 */
	public route getRouteById(String route_id);

}
