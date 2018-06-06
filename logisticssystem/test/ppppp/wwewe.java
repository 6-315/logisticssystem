package ppppp;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.logistics.domain.Testdomain;
import com.logistics.test.service.TestService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext*.xml" })
public class wwewe {
	@Resource
	private TestService testService;

	public TestService getTestService() {
		return testService;
	}

	public void setTestService(TestService testService) {
		this.testService = testService;
	}

	@Test
	public void ppp() {
		Testdomain w = new Testdomain();
		w.setTest1("888");
		w.setTest2("88wewe8");
		testService.Add(w);
	}
	@Test
	public void ooo() {
		List<Testdomain>  listw = testService.select();
		System.out.println("iodisoidosidoi:"+listw);
		
	}

}
