package com.ubsoft.framework.system.entity;

import com.ubsoft.framework.core.dal.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;



@Table(name="SA_LOOKUP")
@Entity
public class Lookup extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="LKKEY",length=32,unique=true,nullable=false) 
	private String lkKey;		
	
	@Column(name="LKNAME",length=50,nullable=false) 
	private String lkName;	
	@Column(name="REMARKS",length=255) 
	private String remarks;	
	public String getLkKey() {
		return lkKey;
	}
	public void setLkKey(String lkKey) {
		this.lkKey = lkKey;
	}
	public String getLkName() {
		return lkName;
	}
	public void setLkName(String lkName) {
		this.lkName = lkName;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	
	
	
	
	
   

}