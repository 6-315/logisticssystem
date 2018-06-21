package com.logistics.distribution.dao;

import java.util.List;

import com.logistics.domain.unit;

/**
 * 配送点管理的DAO层接口
 * 
 * @author LW
 *
 */
public interface DistributionDao {
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
	 * 根据id查询配送点
	 * 
	 * @param unit_id
	 * @return
	 */
	public unit getDistributionInfoById(String trim);
/**
 * 查询最大编号
 * @param unit_num
 * @return
 */
	public String getDistributionByNum(String unit_num);

	/**
	 * 
	 * @param trim
	 * @return
	 */

}
