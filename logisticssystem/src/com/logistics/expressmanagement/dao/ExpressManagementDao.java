package com.logistics.expressmanagement.dao;

import java.util.List;

import com.logistics.domain.distributiontor;
import com.logistics.domain.express;
import com.logistics.domain.express_circulation;
import com.logistics.domain.express_route;
import com.logistics.domain.reservation;
import com.logistics.domain.staff_basicinfo;
import com.logistics.domain.unit;
import com.logistics.domain.vehicle;
/**
 * DAO接口
 * @author LW 
 *
 */
public interface ExpressManagementDao {
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

	public reservation getReservationInfoById(String reservation_id);

	public distributiontor getDistributorInfoById(String distributiontor_id);

	public distributiontor getDistributorInfoByBasicInfo(String staff_id);

	public reservation getReservationInfoByDistributorId(String distributiontor_id);

	public staff_basicinfo getStaffInfoById(String staff_superiorleader);

	public unit getUnitInfoById(String staff_unit);

	public express getExpressById(String express_id);

	public express_route getExpressRouteInfoByExpressId(String express_id);

	public String getMaxNumber(String hql);

	public vehicle getVehicleInfoById(String vehicle_id);

	public express_circulation getExpressCirculationInfoByExpressIdAndReceiver(String express_id, String staff_unit);


	/**
	 * 
	 * @param trim
	 * @return
	 */


}
