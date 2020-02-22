package com.ubsoft.framework.core.dal.entity;


import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import java.io.Serializable;
import java.sql.Timestamp;
@MappedSuperclass
public abstract class BaseEntity implements Serializable {
	@Id
	//@KeySql(genId = UUIDGenId.class)
	@Column(name="ID")
	protected String id;
	@Version
	@Column(name = "VERSION")
	protected Integer version;
	@Column(name = "CREATEDBY", length = 32)
	protected String createdBy;
	@Column(name = "STATUS", length = 10)
	protected String status;

	@Column(name = "CREATEDTIME")
	protected Timestamp createdTime;

	@Column(name = "UPDATEDBY", length = 32)
	protected String updatedBy;

	@Column(name = "UPDATEDTIME")
	protected Timestamp updatedTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}



	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Timestamp getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Timestamp createdDate) {
		this.createdTime = createdDate;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Timestamp getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Timestamp updatedTime) {
		this.updatedTime = updatedTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
