package com.ubsoft.framework.system.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
@Entity
@Table(name="SA_USER_DIMENSION")  
public class UserDimension  implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID",length=32,nullable=false)
	protected String id;
	@Column(name="USERKEY",length=32,nullable=false) 
	private String userKey;			
	@Column(name="DIMKEY",length=32,nullable=false) 	
	private String dimKey;	
	@Column(name="DIMVALUE",length=32,nullable=false) 	
	private String dimValue;
	public String getUserKey() {
		return userKey;
	}
	public void setUserKey(String userKey) {
		this.userKey = userKey;
	}
	public String getDimKey() {
		return dimKey;
	}
	public void setDimKey(String dimKey) {
		this.dimKey = dimKey;
	}
	public String getDimValue() {
		return dimValue;
	}
	public void setDimValue(String dimValue) {
		this.dimValue = dimValue;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	

}