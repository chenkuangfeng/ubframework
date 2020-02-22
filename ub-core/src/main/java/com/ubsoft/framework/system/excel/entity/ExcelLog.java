package com.ubsoft.framework.system.excel.entity;

import com.ubsoft.framework.core.dal.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
@Entity
@Table(name = "SC_EXCEL_LOG")
public class ExcelLog extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	@Column(length = 36, name = "PID")
	private String pId;
	@Column(length = 36, name = "SERVICENAME")
	private String serviceName;
	
	@Column(length = 255, name = "REMARKS")
	private String remarks;

	@Column(length = 255, name = "C1")
	private String c1;

	@Column(length = 255, name = "C2")
	private String c2;
	
	@Column(length = 255, name = "C3")
	private String c3;
	
	@Column(length = 255, name = "C4")
	private String c4;
	
	@Column(length = 255, name = "C5")
	private String c5;
	
	@Column(length = 255, name = "C6")
	private String c6;
	
	@Column(length = 255, name = "C7")
	private String c7;
	
	@Column(length = 255, name = "C8")
	private String c8;
	
	@Column(length = 255, name = "C9")
	private String c9;
	@Column(length = 255, name = "C10")
	private String c10;
	
	@Column(length = 255, name = "C11")
	private String c11;
	@Column(length = 255, name = "C12")
	private String c12;
	
	@Column(length = 255, name = "C13")
	private String c13;
	@Column(length = 255, name = "C14")
	private String c14;
	
	@Column(length = 255, name = "C15")
	private String c15;
	@Column(length = 255, name = "C16")
	private String c16;
	
	@Column(length = 255, name = "C17")
	private String c17;
	@Column(length = 255, name = "C18")
	private String c18;
	
	@Column(length = 255, name = "C19")
	private String c19;
	
	@Column(length = 255, name = "C20")
	private String c20;
	
	@Column(length = 255, name = "C21")
	private String c21;
	@Column(length = 255, name = "C22")
	private String co22;
	
	@Column(length = 255, name = "C23")
	private String c23;
	@Column(length = 255, name = "C24")
	private String c24;
	
	@Column(length = 255, name = "C25")
	private String c25;
	@Column(length = 255, name = "C26")
	private String c26;
	
	@Column(length = 255, name = "C27")
	private String c27;
	
	@Column(length = 255, name = "C28")
	private String c28;
	
	@Column(length = 255, name = "C29")
	private String c29;
	@Column(length = 255, name = "C30")
	private String c30;
	
	@Column(length = 255, name = "C31")
	private String c31;
	@Column(length = 255, name = "C32")
	private String c32;
	
	@Column(length = 255, name = "C33")
	private String c33;
	@Column(length = 255, name = "C34")
	private String c34;
	
	@Column(length = 255, name = "C35")
	private String c35;
	@Column(length = 255, name = "C36")
	private String c36;
	
	@Column(length = 255, name = "C37")
	private String c37;
	@Column(length = 255, name = "C38")
	private String c38;	
	@Column(length = 255, name = "C39")
	private String c39;
	@Column(length = 255, name = "C40")
	private String c40;
	
	@Column(length = 255, name = "C41")
	private String c41;
	@Column(length = 255, name = "C42")
	private String c42;
	
	@Column(length = 255, name = "C43")
	private String c43;
	@Column(length = 255, name = "C44")
	private String c44;
	
	@Column(length = 255, name = "C45")
	private String c45;
	@Column(length = 255, name = "C46")
	private String c46;
	
	@Column(length = 255, name = "C47")
	private String c47;
	@Column(length = 255, name = "C48")
	private String c48;
	
	@Column(length = 255, name = "C49")
	private String co49;
	@Column(length = 255, name = "C50")
	private String c50;
	public String getC1() {
		return c1;
	}
	public void setC1(String c1) {
		this.c1 = c1;
	}
	public String getC2() {
		return c2;
	}
	public void setC2(String c2) {
		this.c2 = c2;
	}
	public String getC3() {
		return c3;
	}
	public void setC3(String c3) {
		this.c3 = c3;
	}
	public String getC4() {
		return c4;
	}
	public void setC4(String c4) {
		this.c4 = c4;
	}
	public String getC5() {
		return c5;
	}
	public void setC5(String c5) {
		this.c5 = c5;
	}
	public String getC6() {
		return c6;
	}
	public void setC6(String c6) {
		this.c6 = c6;
	}
	public String getC7() {
		return c7;
	}
	public void setC7(String c7) {
		this.c7 = c7;
	}
	public String getC8() {
		return c8;
	}
	public void setC8(String c8) {
		this.c8 = c8;
	}
	public String getC9() {
		return c9;
	}
	public void setC9(String c9) {
		this.c9 = c9;
	}
	public String getC10() {
		return c10;
	}
	public void setC10(String c10) {
		this.c10 = c10;
	}
	public String getC11() {
		return c11;
	}
	public void setC11(String c11) {
		this.c11 = c11;
	}
	public String getC12() {
		return c12;
	}
	public void setC12(String c12) {
		this.c12 = c12;
	}
	public String getC13() {
		return c13;
	}
	public void setC13(String c13) {
		this.c13 = c13;
	}
	public String getC14() {
		return c14;
	}
	public void setC14(String c14) {
		this.c14 = c14;
	}
	public String getC15() {
		return c15;
	}
	public void setC15(String c15) {
		this.c15 = c15;
	}
	public String getC16() {
		return c16;
	}
	public void setC16(String c16) {
		this.c16 = c16;
	}
	public String getC17() {
		return c17;
	}
	public void setC17(String c17) {
		this.c17 = c17;
	}
	public String getC18() {
		return c18;
	}
	public void setC18(String c18) {
		this.c18 = c18;
	}
	public String getC19() {
		return c19;
	}
	public void setC19(String c19) {
		this.c19 = c19;
	}
	public String getC20() {
		return c20;
	}
	public void setC20(String c20) {
		this.c20 = c20;
	}
	public String getC21() {
		return c21;
	}
	public void setC21(String c21) {
		this.c21 = c21;
	}
	public String getCo22() {
		return co22;
	}
	public void setCo22(String co22) {
		this.co22 = co22;
	}
	public String getC23() {
		return c23;
	}
	public void setC23(String c23) {
		this.c23 = c23;
	}
	public String getC24() {
		return c24;
	}
	public void setC24(String c24) {
		this.c24 = c24;
	}
	public String getC25() {
		return c25;
	}
	public void setC25(String c25) {
		this.c25 = c25;
	}
	public String getC26() {
		return c26;
	}
	public void setC26(String c26) {
		this.c26 = c26;
	}
	public String getC27() {
		return c27;
	}
	public void setC27(String c27) {
		this.c27 = c27;
	}
	public String getC28() {
		return c28;
	}
	public void setC28(String c28) {
		this.c28 = c28;
	}
	public String getC29() {
		return c29;
	}
	public void setCo29(String co29) {
		this.c29 = c29;
	}
	public String getC30() {
		return c30;
	}
	public void setC30(String c30) {
		this.c30 = c30;
	}
	public String getC31() {
		return c31;
	}
	public void setC31(String c31) {
		this.c31 = c31;
	}
	public String getC32() {
		return c32;
	}
	public void setC32(String c32) {
		this.c32 = c32;
	}
	public String getC33() {
		return c33;
	}
	public void setC33(String c33) {
		this.c33 = c33;
	}
	public String getC34() {
		return c34;
	}
	public void setC34(String c34) {
		this.c34 = c34;
	}
	public String getC35() {
		return c35;
	}
	public void setC35(String c35) {
		this.c35 = c35;
	}
	public String getC36() {
		return c36;
	}
	public void setC36(String c36) {
		this.c36 = c36;
	}
	public String getC37() {
		return c37;
	}
	public void setC37(String c37) {
		this.c37 = c37;
	}
	public String getC38() {
		return c38;
	}
	public void setC38(String c38) {
		this.c38 = c38;
	}
	public String getC39() {
		return c39;
	}
	public void setC39(String c39) {
		this.c39 = c39;
	}
	public String getC40() {
		return c40;
	}
	public void setC40(String c40) {
		this.c40 = c40;
	}
	public String getC41() {
		return c41;
	}
	public void setC41(String c41) {
		this.c41 = c41;
	}
	public String getC42() {
		return c42;
	}
	public void setC42(String c42) {
		this.c42 = c42;
	}
	public String getC43() {
		return c43;
	}
	public void setC43(String c43) {
		this.c43 = c43;
	}
	public String getC44() {
		return c44;
	}
	public void setC44(String c44) {
		this.c44 = c44;
	}
	public String getC45() {
		return c45;
	}
	public void setC45(String c45) {
		this.c45 = c45;
	}
	public String getC46() {
		return c46;
	}
	public void setC46(String c46) {
		this.c46 = c46;
	}
	public String getC47() {
		return c47;
	}
	public void setC47(String c47) {
		this.c47 = c47;
	}
	public String getC48() {
		return c48;
	}
	public void setC48(String c48) {
		this.c48 = c48;
	}
	public String getCo49() {
		return co49;
	}
	public void setCo49(String co49) {
		this.co49 = co49;
	}
	public String getC50() {
		return c50;
	}
	public void setC50(String c50) {
		this.c50 = c50;
	}
	public String getpId() {
		return pId;
	}
	public void setpId(String pId) {
		this.pId = pId;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	
	
	

}