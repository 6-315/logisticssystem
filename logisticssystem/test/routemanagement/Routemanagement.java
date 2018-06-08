package routemanagement;

import javax.annotation.Resource;

import org.hibernate.service.jta.platform.internal.SynchronizationRegistryBasedSynchronizationStrategy;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.logistics.domain.route;
import com.logistics.routemanagement.RouteManagerVO.RouteManagerVO;
import com.logistics.routemanagement.service.RouteManagementService;

/**
 * 路线管理测试方法
 * 
 * @author LW
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext*.xml" })
public class Routemanagement {
	@Resource
	private RouteManagementService routeManagementService;

	public RouteManagementService getRouteManagementService() {
		return routeManagementService;
	}

	public void setRouteManagementService(RouteManagementService routeManagementService) {
		this.routeManagementService = routeManagementService;
	}

	/**
	 * 增加路线信息
	 */
	@Test

	public void addRouteInfo() {
		route rout = new route();
		rout.setRoute_num("6");
		System.out.println("测试。。" + rout.getRoute_num());
		routeManagementService.addRout(rout);
	}

	/**
	 * 更改路线信息
	 */
	@Test
	public void updateRouteInfo() {
		route updateRoute = new route();
		updateRoute.setRoute_id("cfa5b124caf5");
		updateRoute.setRoute_num("8");
		System.out.println("ddddddddddd");
		System.out.println("测试。。" + updateRoute.getRoute_num());
		routeManagementService.updateRoutInfo(updateRoute);

	}

	/**
	 * 批量删除测试
	 */
	@Test
	public void deleteListRoute() {
		String ids="acccd55b-f62c-4760-9735-cfa5b124caf5,684ebf12-f4d8-4af1-9e32-801774b3356b,34e474ac-9575-4900-9a37-f417fe0b7cef";
		System.out.println("删除成功");
		routeManagementService.deleteListRoute(ids);
				
	}
	
	/**
	 * 显示列表和分页
	 */
	@Test
	public void getRouteManager() {
		RouteManagerVO routeManagerVO = new RouteManagerVO();
		System.out.println(routeManagementService.getRouteManagerVO(routeManagerVO).getTotalPages());
		System.out.println(routeManagementService.getRouteManagerVO(routeManagerVO).getTotalRecords());
		System.out.println("--------------------------------------");
		System.out.println(routeManagementService.getRouteManagerVO(routeManagerVO).getListRouteManagerDTO().get(0));
	}
	/**
	 * 各种根据搜索
	 */
	@Test
	public void searchForm(){
		String search="1";
	}
}
