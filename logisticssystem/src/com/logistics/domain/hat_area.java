package com.logistics.domain;

public class hat_area {
	private String id;
	private String areaID;
	private String area;
	private String father;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAreaID() {
		return areaID;
	}

	public void setAreaID(String areaID) {
		this.areaID = areaID;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getFather() {
		return father;
	}

	public void setFather(String father) {
		this.father = father;
	}

	@Override
	public String toString() {
		return "hat_area [id=" + id + ", areaID=" + areaID + ", area=" + area + ", father=" + father + "]";
	}

}
