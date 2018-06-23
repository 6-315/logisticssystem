package routemanagement;

import java.text.DecimalFormat;

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
		System.out.println();
		routeManagementService.addRout(rout);
	}
/*	@Test
	    public void mai2n() {
	        String a = "A001";
	        String b = a.substring(0,1);
	        String c = a.substring(1,4);
	        int d = Integer.parseInt(c);
	        d=d+1;
	        DecimalFormat df=new DecimalFormat("000");
	        String xty = df.format(d);
	        System.out.println("xty是："+xty);
	        System.out.println(b+xty);
	        String z = b+(d+1);
	        System.out.println(z);
	}*/
	@Test
	public void ssss() {
		String ss = "A009";
		String xjc= ss.substring(3);
		int an = Integer.parseInt(xjc);
		int xxx=an+1;
		DecimalFormat df=new DecimalFormat("A000");
		df.format(xxx);
		System.out.println("sssssssss:"+df.format(xxx));
	}

	/**
	 * 更改路线信息
	 */
	@Test
	public void updateRouteInfo() {
		route updateRoute = new route();
		updateRoute.setRoute_id("a8af69ca-5ccc-4925-9d19-7ee783c27c11");
		updateRoute.setRoute_num("A006");
		System.out.println("ddddddddddd");
		System.out.println("测试。。" + updateRoute.getRoute_num());
		routeManagementService.updateRoutInfo(updateRoute);

	}

	/**
	 * 批量删除测试
	 */
	@Test
	public void deleteListRoute() {
		String ids="79c11d1f-7b77-4fed-b6e9-77a6299167c8,a8af69ca-5ccc-4925-9d19-7ee783c27c11";
		System.out.println("删除成功");
		routeManagementService.removeListRoute(ids);
				
	}
	
	/**
	 * 显示列表和分页
	 */
	@Test
	public void getRouteManager() {
		RouteManagerVO routManagerVO = new RouteManagerVO();
		routManagerVO = routeManagementService.getRouteManagerVO(routManagerVO);
		System.out.println("给爸爸来/1"+routManagerVO);
	}


}
