package com.ubsoft.framework.job.entity;

import com.ubsoft.framework.core.dal.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Timestamp;

/**
 * 
 * @ClassName: TaskLog
 * @Description: 调度任务日志
 * @author chenkf
 * @date 2017-2-22 下午12:50:54
 * @version V1.0
 */

@Entity
@Table(name = "SA_JOB_LOG")
public class JobLog extends BaseEntity {
	@Column(name="JOBKEY",length = 50, nullable = false)
	private String jobKey;
	@Column(name="SERVER",length = 50, nullable = false)
	private String server;
	@Column(name="STARTTIME")
	private Timestamp startTime;
	@Column(name="ENDTIME")
	private Timestamp endTime;
	@Column(name="LOGLEVEL",length = 30, nullable = false)
	private String logLevel;// 日志级别
	
	@Column(name="MSG")
	private String msg;

	public String getJobKey() {
		return jobKey;
	}

	public void setJobKey(String jobKey) {
		this.jobKey = jobKey;
	}

	public String getServer() {
		return server;
	}

	public void setServer(String server) {
		this.server = server;
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

	

	public String getLogLevel() {
		return logLevel;
	}

	public void setLogLevel(String logLevel) {
		this.logLevel = logLevel;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
