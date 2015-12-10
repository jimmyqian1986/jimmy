/**
 * 
 */
package com.jimmy.hsp.class1;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author jimmy
 * @category 简单的员工管理系统的入口和管理类 请做一个公司职员薪水管理系统，要求完成如下功能： 1、当有新员工时，将该员工加入到管理系统
 * @category 可以根据员工号，显示该员工的信息 3、可以显示所有员工信息 4、可以修改员工的薪水 5、当员工离职时，将该员工从管理系统中删除
 * @category 可以按照薪水从低到高顺序排序[思考题] 7、可以统计员工的平均工资和最低和最高工资
 */
public class Demo7 {
	static ArrayList<Employer> emplist = new ArrayList<Employer>();
	static String no = "";
	static String name = "";
	static float sal = 0;

	public static void main(String[] args) {
		Scanner sr = new Scanner(System.in);
		Demo7 dm7 = new Demo7();
		while (true) {
			System.out.println("公司职员薪水管理系统");
			System.out.println("1、录入新员工");
			System.out.println("2、根据工号查询信息");
			System.out.println("3、查询所有员工信息");
			System.out.println("4、通过工号修改员工薪水");
			System.out.println("5、删除员工信息");
			System.out.println("6、按薪水高低排序");
			System.out.println("7、计算平均工资及最高(低)工资");
			System.out.println("0、退出系统");
			System.out.print("请输入对应的数字进行操作：");
			int sel = sr.nextInt();
			if (sel == 1) {
				System.out.println("请录入新员工的信息");
				System.out.print("工号:");
				String empNo = sr.next();
				System.out.print("姓名:");
				String name = sr.next();
				System.out.print("工资:");
				float sal = sr.nextFloat();
				// 构建emp对象
				Employer emp = new Employer(no, name, sal);
				// 将empNo,name,sal的值传给构造函数Emp
				dm7.newEmp(emp);
				System.out.println("创建新员工" + name + "成功!");
			} else if (sel == 2) {
				System.out.println("请录入员工工号：");
				String empNo = sr.next();
				// int index=
				dm7.showInfo(empNo);
			} else if (sel == 3) {
				System.out.println("公司所有员工信息如下：");
				dm7.selectAllEmp();
			} else if (sel == 4) {
				System.out.println("请输入工号：");
				String empNo = sr.next();
				System.out.println("将工资修改为：");
				float newSal = sr.nextFloat();
				// dm7.updateSal(empNo, newSal);
			} else if (sel == 5) {
				System.out.println("请输入要删除人员的工号：");
				String empNo = sr.next();

				dm7.delEmp(dm7.getEmp(empNo));
			} else if (sel == 6) {
				System.out.println("已按薪资高低进行排序如下：");
				// dm7.SortSal();
			} else if (sel == 7) {
				System.out.println("显示平均工资及最高、最低工资人员信息如下：");
				// dm7.Average();
			} else if (sel == 0) {
				System.out.println("已正常退出!");
				System.exit(0);
			} else {
				System.out.println("输入错误，请重新输入!");
			}
		}
	}

	public int getIndex(Employer emp) {
		int index = 0;
		try {
			emplist.indexOf(emp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("查询序列错误");
			System.exit(1);
		}
		return index;
	}

	/**
	 * 向队列中添加员工对象
	 * 
	 * @param emp
	 */
	public void newEmp(Employer emp) {
		try {
			emplist.add(emp);
			System.out.println("增加新员工成功!," + emp.empName + " 欢迎您的加入!");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("添加员工出错，程序退出");
			System.exit(1);
		}
	}

	/**
	 * 删除员工对象
	 * 
	 * @param emp
	 */
	public void delEmp(Employer emp) {

		try {
			emplist.remove(emp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("删除员工出错，程序退出");
			System.exit(1);
		}
	}

	/**
	 * 查询员工信息
	 * 
	 * @param emp
	 */
	public void selectEmp(Employer emp) {
		try {
			System.out.println("员工工号是：" + emp.empNo + ",姓名：" + emp.empName
					+ ",薪资是：" + emp.empSal);

		} catch (Exception e) {
			// TODO: handle exception

		}
	}

	/**
	 * 查询所有员工信息
	 */
	public void selectAllEmp() {
		Employer emp = null;
		for (int i = 0; i < emplist.size(); i++) {
			emp = emplist.get(i);
			try {
				System.out.println("员工工号是：" + emp.empNo + ",姓名：" + emp.empName
						+ ",薪资是：" + emp.empSal);

			} catch (Exception e) {
				// TODO: handle exception

			}
		}
	}

	/**
	 * 根据
	 * 
	 * @param index
	 * @return Employer
	 */
	public Employer getEmp(String empNo) {
		Employer emp = null;
		boolean done = false;
		while (done == false) {
			for (int i = 0; i < emplist.size(); i++) {
				if (emplist.get(i).getEmpNo().equals(empNo)) {
					done = true;
					emp = emplist.get(i);
				}
			}
		}

		return emp;
	}

	// 根据员工工号显示员工的相关信息
	public void showInfo(String empNo) {// 将工号传入showInfo方法中
		// 遍历整个ArrayList
		for (int i = 0; i < emplist.size(); i++) {
			// 取出Emp对象
			Employer emp = (Employer) emplist.get(i);
			// 比较编号
			if (emp.getEmpNo().equals(empNo)) {// 由于empNo类型为String，所以要使用equals进行内容比较，不可以使用==地址比较
				System.out.println("找到该员工，他的信息是：");
				System.out.println("工号:" + empNo + "\t姓号:" + emp.getEmpName()
						+ "\t薪水:" + emp.getEmpSal());
			} else {
				System.out.println("工号不存在或无此人!");
			}
		}
	}
}
