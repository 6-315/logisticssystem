package com.logistics.distribution.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.mapping.Array;

import com.logistics.distribution.DTO.UnitManagerDTO;
import com.logistics.distribution.VO.UnitManagerVO;
import com.logistics.distribution.dao.DistributionDao;
import com.logistics.distribution.service.DistributionService;
import com.logistics.domain.staff_basicinfo;
import com.logistics.domain.unit;

import util.BuildUuid;
import util.TimeUtil;

/**
 * 配送点管理业务实现层
 * 
 * @author LW
 *
 */
public class DistributionServiceImpl implements DistributionService {
	private DistributionDao distributionDao;

	public void setDistributionDao(DistributionDao distributionDao) {
		this.distributionDao = distributionDao;
	}

	/**
	 * 添加配送点业务逻辑实现层
	 */
	@Override
	public int addDistributionAction(unit distribution) {
		distribution.setUnit_id(BuildUuid.getUuid());
		distribution.setUnit_createtime(TimeUtil.getStringSecond());
		distribution.setUnit_modifytime(TimeUtil.getStringSecond());
		distribution.setUnit_state("1");
		System.out.println("start:" + distribution);
		distributionDao.saveOrUpdateObject(distribution);
		return 1;
	}
	/**
	 * 获取单位列表信息和分页显示的业务逻辑
	 */
	@SuppressWarnings("unchecked")
	@Override
	public UnitManagerVO getUnitManagerVO(UnitManagerVO unitManagerVO) {
		
	
		// TODO Auto-generated method stub
		List<UnitManagerDTO> unitManagerDTO=new ArrayList();
		List<unit> distribution=new ArrayList<>();
		
		String selectPaging = "select count(*) from unit where unit_state = '1'";
		String selectTable = "from unit where unit_state = '1'";
		//查询：
		if(unitManagerVO.getSearch()!=null&&unitManagerVO.getSearch().trim().length()>0 ) {
			String search = "%"+unitManagerVO.getSearch()+"%";
			selectPaging = selectPaging + " and unit like '" + search + "' ";
			selectTable = selectTable + " and unit like '" + search + "'";
		}
		// 这里如果不加desc表示正序，如果加**/上desc表示倒序
		
		selectTable = selectTable + "order by unit_state desc";
		int userInfoCount = distributionDao.getCount(selectPaging);
		// 设置总数量
		unitManagerVO.setTotalRecords(userInfoCount);
		// 设置总页数
		unitManagerVO.setTotalPages(((userInfoCount - 1) / unitManagerVO.getPageSize()) + 1);
		// 判断是否拥有上一页
		if (unitManagerVO.getPageIndex() <= 1) {
			unitManagerVO.setHavePrePage(false);
		} else {
			unitManagerVO.setHavePrePage(true);
		}
		// 判断是否拥有下一页
		if (unitManagerVO.getPageIndex() >= unitManagerVO.getTotalPages()) {
			unitManagerVO.setHaveNextPage(false);
		} else {
			unitManagerVO.setHaveNextPage(true);
		}
		distribution = (List<unit>) (distributionDao.queryForPage(selectTable, unitManagerVO.getPageIndex(),
				unitManagerVO.getPageSize()));
		
		distribution=(List<unit>) distributionDao.listObject("from unit where unit_state= 1");
		for (unit unit : distribution) {
			System.out.println("这是什么"+unit.getUnit_admin());
			UnitManagerDTO unitDTO=new UnitManagerDTO();
			unit unit1=new unit();
			staff_basicinfo unitAdmin=new staff_basicinfo();
			List<staff_basicinfo> listStaff = new ArrayList<>();
			List<staff_basicinfo> listStaff1 = new ArrayList<>();
			listStaff =  (List<staff_basicinfo>) distributionDao.listObject(" from staff_basicinfo  where staff_id='"+unit.getUnit_admin()+"'");
			listStaff1 =  (List<staff_basicinfo>) distributionDao.listObject(" from staff_basicinfo  where staff_id='"+unit.getUnit_creator()+"'");
			System.out.println("_____:"+listStaff.get(0).getStaff_num());
			System.out.println("555:"+unitAdmin);
			unitDTO.setUnitAdmin(listStaff.get(0));
			unitDTO.setUnitCreator(listStaff1.get(0));
			unitDTO.setUnitInfo(unit);
			System.out.println("wwwwwwwwwwwwww"+unitDTO);
			unitManagerDTO.add(unitDTO);
		}
		unitManagerVO.setUnitManagerDTO(unitManagerDTO);
		return unitManagerVO;
	}

}
