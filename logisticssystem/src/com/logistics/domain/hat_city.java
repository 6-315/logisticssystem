package com.logistics.domain;

/**
 * 市
 * 
 * @author JXX
 *
 */
public class hat_city {
	private String id;
	private String cityID;
	private String city;
	private String father;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCityID() {
		return cityID;
	}

	public void setCityID(String cityID) {
		this.cityID = cityID;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getFather() {
		return father;
	}

	public void setFather(String father) {
		this.father = father;
	}

	@Override
	public String toString() {
		return "hat_city [id=" + id + ", cityID=" + cityID + ", city=" + city + ", father=" + father + "]";
	}

}
