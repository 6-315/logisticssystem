package com.logistics.transferstation.DTO;

import com.logistics.domain.staff_basicinfo;
import com.logistics.domain.unit;

/**
 * 中转站DTO
 * @author LL
 *
 */
public class UnitManagerDTO {
private unit unit;
private staff_basicinfo staff_basicinfo;

public staff_basicinfo getStaff_basicinfo() {
	return staff_basicinfo;
}

public void setStaff_basicinfo(staff_basicinfo staff_basicinfo) {
	this.staff_basicinfo = staff_basicinfo;
}

public unit getUnit() {
	return unit;
}

public void setUnit(unit unit) {
	this.unit = unit;
}

@Override
public String toString() {
	return "UnitManagerDTO [unit=" + unit + ", staff_basicinfo=" + staff_basicinfo + "]";
}


}
