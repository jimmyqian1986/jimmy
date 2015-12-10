/**
 * 
 */
package com.jimmy.hsp.class1;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Vector;

/**
 * @author jimmy
 *
 */
public class Demo8 {
	LinkedList<Employer> linkedlist = new LinkedList<Employer>();
	Vector<Employer> vector = new Vector<Employer>();
	HashMap hs = new HashMap();

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Demo8 dm8 = new Demo8();
		for (int i = 0; i < 5; i++) {
			dm8.linkListInsertEmp(i + "", "name" + i, (i * 100) / 12f);
			dm8.vectorInsertEmp(i + "", "name" + i, (i * 100) / 12f);
			dm8.hashMapInsertEmp(i + "", "name" + i, (i * 100) / 12f);
		}
		dm8.linkListGetEmp();
		System.out.println("-=================");
		dm8.vectorGetEmp();
		System.out.println("------------------");
		dm8.hashMapGetEmp();
	}

	/**
 * 
 */
	private void linkListGetEmp() {
		// TODO Auto-generated method stub
		Employer emp = null;
		for (int i = 0; i < linkedlist.size(); i++) {
			emp = linkedlist.get(i);
			System.out.println(emp.getEmpName() + " " + emp.getEmpSal());
		}
	}

	/**
	 * 
	 */
	private void linkListInsertEmp(String empNo, String empName, float empSal) {
		// TODO Auto-generated method stub
		Employer emp = new Employer(empNo, empName, empSal);
		linkedlist.addFirst(emp);
	}

	/**
	 * VECTOR 集合
	 */
	private void vectorGetEmp() {
		// TODO Auto-generated method stub
		Employer emp = null;
		for (int i = 0; i < vector.size(); i++) {
			emp = vector.get(i);
			System.out.println(emp.getEmpName() + " " + emp.getEmpSal());
		}
	}

	/**
	 * 
	 */
	private void vectorInsertEmp(String empNo, String empName, float empSal) {
		// TODO Auto-generated method stub
		Employer emp = new Employer(empNo, empName, empSal);
		vector.add(emp);
	}

	/**
	 * VECTOR 集合
	 */
	private void hashMapGetEmp() {
		// TODO Auto-generated method stub
		Employer emp = null;
		for (int i = 0; i < hs.size(); i++) {
			emp = (Employer) hs.get(i);
			System.out.println(emp.getEmpName() + " " + emp.getEmpSal());
		}
	}

	/**
	 * 
	 */
	private void hashMapInsertEmp(String empNo, String empName, float empSal) {
		// TODO Auto-generated method stub
		Employer emp = new Employer(empNo, empName, empSal);
		hs.put(empNo, emp);
	}
}
