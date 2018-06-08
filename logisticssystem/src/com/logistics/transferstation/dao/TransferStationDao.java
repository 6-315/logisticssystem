package com.logistics.transferstation.dao;

import java.util.List;

import com.logistics.domain.staff_basicinfo;
import com.logistics.domain.unit;
/**
 * 中转站管理 的DAO接口
 * @author LW
 *
 */
public interface TransferStationDao {
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
	 * 根据Id查询中转站
	 * @param unitId
	 * @return
	 */
	public unit getTransferStationInfoById(String unit_id);

	public staff_basicinfo getBasicinfo(String trim);

	
	
	
}
