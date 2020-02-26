package com.ubsoft.framework.job.entity;

import com.ubsoft.framework.core.dal.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Timestamp;

/**
 * 调度任务表
 * 
 * @author Administrator
 * 
 */
@Entity
@Table(name = "SA_JOB")
public class Job extends BaseEntity {
	@Column(name="JOBKEY",length = 32, unique = true, nullable = false)
	private String jobKey;
	@Column(name="JOBNAME",length = 200, nullable = false)
	private String jobName;
	@Column(name="JOBTYPE",length = 100, nullable = false)
	private String jobType;
	@Column(name="CRON",length = 50,nullable = false)
	private String cron;
	@Column(name="SERVICENAME",length = 50, nullable = false)
	private String serviceName;
	@Column(name="DSNAME",length = 50, nullable = false)
	private String dsName;
	@Column(name="ARGS",length = 100)
	private String args;
	@Column(name="SQLVALUE")	
	private String sqlValue;
	@Column(name="STARTTIME")
	private Timestamp startTime;
	@Column(name="ENDTIME")
	private Timestamp endTime;
	@Column(name="REMARKS")
	private String remarks;
	/**
	 * 是否记录日志
	 */
	@Column(name="LOG")
	private boolean log;

	public String getJobKey() {
		return jobKey;
	}

	public void setJobKey(String jobKey) {
		this.jobKey = jobKey;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getJobType() {
		return jobType;
	}

	public void setJobType(String jobType) {
		this.jobType = jobType;
	}

	public String getCron() {
		return cron;
	}

	public void setCron(String cron) {
		this.cron = cron;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getDsName() {
		return dsName;
	}

	public void setDsName(String dsName) {
		this.dsName = dsName;
	}

	public String getArgs() {
		return args;
	}

	public void setArgs(String args) {
		this.args = args;
	}

	public String getSqlValue() {
		return sqlValue;
	}

	public void setSqlValue(String sqlValue) {
		this.sqlValue = sqlValue;
	}

	public Timestamp getStartTime() {
		return startTime;
	}

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	public Timestamp getEndTime() {
		return endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public boolean isLog() {
		return log;
	}

	public void setLog(boolean log) {
		this.log = log;
	}
}
