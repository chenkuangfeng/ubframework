package com.ubsoft.framework.system.entity;

import com.ubsoft.framework.core.dal.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 *代码自动生成 2018-08-16
 *序号 
**/
@Entity
@Table(name="SC_SEQUENCE")
public class Sequence extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;	
	/** 序号编码 **/
    @Column(name="SEQKEY")
    private String seqKey;    
	/** 序号名称 **/
    @Column(name="SEQNAME")   
    private String seqName;    
	/** 模块值 **/
    @Column(name="MODULEVAL")   
    private String moduleVal;    
	/** 规则表达式 **/
    @Column(name="PATTERN")   
    private String pattern;    
	/** 最新值 **/
    @Column(name="NEXTNUM")   
    private int nextNum;    
	
	public String getSeqKey() {
		return seqKey;
	}
	public void setSeqKey(String seqKey) {
		this.seqKey = seqKey;
	}
	public String getSeqName() {
		return seqName;
	}
	public void setSeqName(String seqName) {
		this.seqName = seqName;
	}
	public String getModuleVal() {
		return moduleVal;
	}
	public void setModuleVal(String moduleVal) {
		this.moduleVal = moduleVal;
	}
	public String getPattern() {
		return pattern;
	}
	public void setPattern(String pattern) {
		this.pattern = pattern;
	}
	public int getNextNum() {
		return nextNum;
	}
	public void setNextNum(int nextNum) {
		this.nextNum = nextNum;
	}
}