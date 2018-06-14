package com.logistics.expressmanagementW.dao;

import java.util.List;

import com.logistics.domain.express;
import com.logistics.domain.express_route;
import com.logistics.domain.expressinfo;
import com.logistics.domain.route;
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

	public vehicle getVehicle(String vehicle_id);

	public expressinfo getExpressInfo(String express_expressinfoid);

	public express_route getexpressRoute(String express_id);

	public route getRoute(String express_route_id);


	/**
	 * 
	 * @param trim
	 * @return
	 */


}
