/**
 * 
 */
package com.jimmy.hsp.class1;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Vector;

import jimmy.commons.Log4j;

import org.apache.logging.log4j.Logger;

/**
 * @author jimmy
 *
 */
public class Demo8 {
	static LinkedList<Employer> linkedlist = new LinkedList<Employer>();
	Vector<Employer> vector = new Vector<Employer>();
	HashMap hs = new HashMap();
	Hashtable ht = new Hashtable();
	HashSet<Employer> hset = new HashSet<Employer>();
	static Log4j log4j = new Log4j();
	static Logger logger = log4j.getLogger(Demo8.class.getName());

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Demo8 dm8 = new Demo8();
		// logger.info(System.currentTimeMillis());
		for (int i = 0; i < 5; i++) {
			dm8.linkListInsertEmp(i + "", "name" + i, (i * 100) / 12f);
			dm8.vectorInsertEmp(i + "", "name" + i, (i * 100) / 12f);
			dm8.hashMapInsertEmp(i + "", "name" + i, (i * 100) / 12f);
			dm8.hashTableInsertEmp(i + "", "name" + i, (i * 100) / 12f);
			dm8.hashSetInsertEmp(i + "", "name" + i, (i * 100) / 12f);
		}
		dm8.linkListGetEmp();
		logger.info("======LINKEDLIST========");
		dm8.vectorGetEmp();
		logger.info("---------VECTOR---------");
		dm8.hashMapGetEmp();
		logger.info("=======HASHMAP======");
		dm8.hashTableGetEmp();
		logger.info("=======HASHTABLE====");
		dm8.hashSetGetEmp();
		logger.info("=======HASHSET======");
		dm8.iteratorGetLinkedList(linkedlist);

	}

	/**
 * 
 */
	private void linkListGetEmp() {
		// TODO Auto-generated method stub
		Employer emp = null;
		for (int i = 0; i < linkedlist.size(); i++) {
			emp = linkedlist.get(i);
			logger.info(emp.getEmpName() + " " + emp.getEmpSal());
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
			logger.info(emp.getEmpName() + " " + emp.getEmpSal());
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
	 * HashMap 集合
	 */
	private void hashMapGetEmp() {
		// TODO Auto-generated method stub
		Employer emp = null;
		for (int i = 1; i < hs.size(); i++) {
			emp = (Employer) hs.get(i + "");
			// logger.info(hs.size());
			logger.info(emp.getEmpName() + " " + emp.getEmpSal());
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

	/**
	 * HashMap 集合
	 */
	private void hashTableGetEmp() {
		// TODO Auto-generated method stub
		Employer emp = null;
		for (Iterator it = ht.keySet().iterator(); it.hasNext();) {
			String key = it.next().toString();
			emp = (Employer) ht.get(key);
			// logger.info(hs.size());
			logger.info(emp.getEmpName() + " " + emp.getEmpSal());
		}
	}

	/**
	 * 
	 */
	private void hashTableInsertEmp(String empNo, String empName, float empSal) {
		// TODO Auto-generated method stub
		Employer emp = new Employer(empNo, empName, empSal);
		ht.put(empNo, emp);
	}

	private void hashSetGetEmp() {
		// TODO Auto-generated method stub
		Employer emp = null;
		Object[] al = hset.toArray();
		for (int i = 0; i < al.length; i++) {
			emp = (Employer) al[i];
			// logger.info(hs.size());
			logger.info(emp.getEmpName() + " " + emp.getEmpSal());
		}
	}

	/**
	 * 
	 */
	private void hashSetInsertEmp(String empNo, String empName, float empSal) {
		// TODO Auto-generated method stub
		Employer emp = new Employer(empNo, empName, empSal);
		hset.add(emp);
	}

	/**
	 * 使用迭代器获取信息
	 * 
	 * @param linlist
	 */
	private void iteratorGetLinkedList(LinkedList linlist) {
		for (Iterator it = linlist.iterator(); it.hasNext();) {
			Employer emp = (Employer) it.next();
			logger.info("use Iterator to get :" + emp.getEmpName() + " "
					+ emp.getEmpSal());
		}
	}
}
