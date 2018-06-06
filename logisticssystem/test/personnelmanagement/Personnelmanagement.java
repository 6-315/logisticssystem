package personnelmanagement;

import javax.annotation.Resource;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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

}
