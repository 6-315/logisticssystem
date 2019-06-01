package com.logistics.expressmanagementW.dao;

import java.util.List;

import com.logistics.domain.distributiontor;
import com.logistics.domain.driver;
import com.logistics.domain.express;
import com.logistics.domain.express_circulation;
import com.logistics.domain.express_route;
import com.logistics.domain.express_send;
import com.logistics.domain.expressinfo;
import com.logistics.domain.position;
import com.logistics.domain.route;
import com.logistics.domain.staff_basicinfo;
import com.logistics.domain.team;
import com.logistics.domain.unit;
import com.logistics.domain.vehicle;
/**
 * DAO接口
 * @author LW 
 *
 */
public interface ExpressManagementDao2 {
	/**
	 * 保存、更新对象
	 * W
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

	public express getExpress(String express_id);

	public vehicle getVehicle(String driver_vehicle);

	public expressinfo getExpressInfo(String express_expressinfoid);

	public express_route getexpressRoute(String express_id);

	public route getRoute(String express_route_id);

	public distributiontor getDistributiontor(String staff_id);

	public express_send getExpressSend(String express_id);

	public team getTeam(String route_id);

	public unit getUnitById(String unitId);

	public staff_basicinfo getStaffBasicinfo(String distributiontor_basicinfo);

	public position getPosition(String staff_id);

	public unit getUpUnit(String staff_unit);

	public driver getDriverById(String staff_id);

	public express_circulation getExpressCirculation(String id);

	express_send getExpressSend1(String express_id);

	public express getExpressByWaybillNumber(String waybillNumber);


	/**
	 * 
	 * @param trim
	 * @return
	 */


}
