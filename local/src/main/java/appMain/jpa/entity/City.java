package appMain.jpa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table
@Entity
public class City {

	@Id
	private int cityid  ;
	
	@Column
	private String city_name ;
	@Column
	private int city_pincode;


	public int getCityid() {
		return cityid;
	}

	public void setCityid(int cityid) {
		this.cityid = cityid;
	}

	public String getCity_name() {
		return city_name;
	}

	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}

	public int getCity_pincode() {
		return city_pincode;
	}

	public void setCity_pincode(int city_pincode) {
		this.city_pincode = city_pincode;
	}
}
