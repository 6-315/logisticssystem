package personnelmanagement;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.logistics.domain.position;
import com.logistics.domain.staff_basicinfo;
import com.logistics.domain.unit;
import com.logistics.personnelmanagement.VO.StaffManagerVO;
import com.logistics.personnelmanagement.service.PersonnelManagementService;

/**
 * 人事管理测试方法
 * 
 * @author LW
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext*.xml" })
public class Personnelmanagement {
	@Resource 
	private PersonnelManagementService personnelManagementService;

	public PersonnelManagementService getPersonnelManagementService() {
		return personnelManagementService;
	}

	public void setPersonnelManagementService(PersonnelManagementService personnelManagementService) {
		this.personnelManagementService = personnelManagementService;
	}


	/**
	 * 查看自身以下的所有职位
	 */
	@Test
	public void getLowerPosition() {
		staff_basicinfo staffBasicInfo = new staff_basicinfo();
		staffBasicInfo.setStaff_id("2");
		staffBasicInfo.setStaff_position("77e07c34-735f-45d4-a870-3e5bebe5ddc3");
		List<position> listPosition = new ArrayList<>();
		listPosition = personnelManagementService.getLowerPosition(staffBasicInfo);
		System.out.println("你是什么鬼！" + listPosition);

	}
	/**
	 * 查看自身一下的所有单位
	 */
	@Test
	public void getUnit() {
		List<unit> listUnit = new ArrayList<>();
		staff_basicinfo staffBasicInfo = new staff_basicinfo();
		staffBasicInfo.setStaff_id("2");
		staffBasicInfo.setStaff_position("77e07c34-735f-45d4-a870-3e5bebe5ddc2");
		listUnit = personnelManagementService.getLowerUnit(staffBasicInfo);
		System.out.println("单位："+listUnit);
	}
	/**
	 * 批量删除员工
	 */
	@Test
	public void deleteListStaff() {
		String staffListIdS = "1,2,3,4,5";
		personnelManagementService.deleteListStaff(staffListIdS);

	}
	/**
	 * 测试修改单位
	 */
	@Test
	public void updateStaffUnit() {
		staff_basicinfo staffBasicInfo = new staff_basicinfo();
		staffBasicInfo.setStaff_id("6");
		staffBasicInfo.setStaff_unit("9999电动车999");
		personnelManagementService.updateStaffUnit(staffBasicInfo);
	}
	
/**
 * 测试修改职位
 */
	@Test
	public void updateStaffPosition() {
		staff_basicinfo staffBasicInfo = new staff_basicinfo();
		staffBasicInfo.setStaff_id("6");
		staffBasicInfo.setStaff_position("9999dd999");
		personnelManagementService.updateStaffPosition(staffBasicInfo);
		
	}
	/**
	 * 根据权限查人
	 */
	@Test
	public void getPeopleBy() {
		StaffManagerVO staffManagerVO = new StaffManagerVO();
		staff_basicinfo staffBasicInfo = new staff_basicinfo();
		staffBasicInfo.setStaff_unit("4");
		staffBasicInfo.setStaff_id("ddsa");
		staffBasicInfo.setStaff_position("77e07c34-735f-45d4-a870-3e5bebe5ddc3");
		staffManagerVO.setSearch("李伟");
		staffManagerVO = personnelManagementService.getStaffManagerVO(staffManagerVO, staffBasicInfo);
		System.out.println("这是什么？："+staffManagerVO);
	}

	/**
	 * 查询自身以下的所有职位
	 */
	
}
