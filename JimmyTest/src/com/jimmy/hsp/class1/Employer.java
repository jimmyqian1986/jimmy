/**
 * 
 */
package com.jimmy.hsp.class1;

/**
 * @author jimmy
 *
 */
public class Employer {
	String empNo;
	String empName;
	float empSal;

	public Employer(String empNo, String empName, float empSal) {
		this.empNo = empNo;
		this.empName = empName;
		this.empSal = empSal;
	}

	/**
	 * @return the empNo
	 */
	public String getEmpNo() {
		return empNo;
	}

	/**
	 * @param empNo
	 *            the empNo to set
	 */
	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}

	/**
	 * @return the empName
	 */
	public String getEmpName() {
		return empName;
	}

	/**
	 * @param empName
	 *            the empName to set
	 */
	public void setEmpName(String empName) {
		this.empName = empName;
	}

	/**
	 * @return the empSal
	 */
	public float getEmpSal() {
		return empSal;
	}

	/**
	 * @param empSal
	 *            the empSal to set
	 */
	public void setEmpSal(float empSal) {
		this.empSal = empSal;
	}
}
