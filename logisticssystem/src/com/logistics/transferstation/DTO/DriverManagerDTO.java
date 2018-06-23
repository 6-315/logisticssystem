package com.logistics.transferstation.DTO;

import com.logistics.domain.driver;
import com.logistics.domain.staff_basicinfo;

public class DriverManagerDTO {

	private staff_basicinfo staff_basicinfo;
	
	private driver driver;

	public staff_basicinfo getStaff_basicinfo() {
		return staff_basicinfo;
	}

	public void setStaff_basicinfo(staff_basicinfo staff_basicinfo) {
		this.staff_basicinfo = staff_basicinfo;
	}

	public driver getDriver() {
		return driver;
	}

	public void setDriver(driver driver) {
		this.driver = driver;
	}

	@Override
	public String toString() {
		return "DriverManagerDTO [staff_basicinfo=" + staff_basicinfo + ", driver=" + driver + "]";
	}
	
	
}
