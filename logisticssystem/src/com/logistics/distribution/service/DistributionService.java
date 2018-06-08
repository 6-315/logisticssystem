package com.logistics.distribution.service;

import com.logistics.distribution.VO.UnitManagerVO;
import com.logistics.domain.unit;

/**
 * 配送点管理的service接口
 * @author LW
 *
 */
public interface DistributionService {

	int addDistributionAction(unit distribution);

	UnitManagerVO getUnitManagerVO(UnitManagerVO unitManagerVO);


}
