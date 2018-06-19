package com.logistics.domain;

/**
 * 省
 * 
 * @author JXX
 *
 */
public class hat_province {
	private String id;
	private String provinceID;
	private String province;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProvinceID() {
		return provinceID;
	}

	public void setProvinceID(String provinceID) {
		this.provinceID = provinceID;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	@Override
	public String toString() {
		return "hat_province [id=" + id + ", provinceID=" + provinceID + ", province=" + province + "]";
	}

}
