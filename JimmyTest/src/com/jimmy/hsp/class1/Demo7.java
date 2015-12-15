/**
 * 
 */
package com.jimmy.hsp.class1;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author jimmy
 * @category �򵥵�Ա������ϵͳ����ں͹����� ����һ����˾ְԱнˮ����ϵͳ��Ҫ��������¹��ܣ� 1��������Ա��ʱ������Ա�����뵽����ϵͳ
 * @category ���Ը���Ա���ţ���ʾ��Ա������Ϣ 3��������ʾ����Ա����Ϣ 4�������޸�Ա����нˮ 5����Ա����ְʱ������Ա���ӹ���ϵͳ��ɾ��
 * @category ���԰���нˮ�ӵ͵���˳������[˼����] 7������ͳ��Ա����ƽ�����ʺ���ͺ���߹���
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
			System.out.println("��˾ְԱнˮ����ϵͳ");
			System.out.println("1��¼����Ա��");
			System.out.println("2�����ݹ��Ų�ѯ��Ϣ");
			System.out.println("3����ѯ����Ա����Ϣ");
			System.out.println("4��ͨ�������޸�Ա��нˮ");
			System.out.println("5��ɾ��Ա����Ϣ");
			System.out.println("6����нˮ�ߵ�����");
			System.out.println("7������ƽ�����ʼ����(��)����");
			System.out.println("0���˳�ϵͳ");
			System.out.print("�������Ӧ�����ֽ��в�����");
			int sel = sr.nextInt();
			if (sel == 1) {
				System.out.println("��¼����Ա������Ϣ");
				System.out.print("����:");
				String empNo = sr.next();
				System.out.print("����:");
				String name = sr.next();
				System.out.print("����:");
				float sal = sr.nextFloat();
				// ����emp����
				Employer emp = new Employer(no, name, sal);
				// ��empNo,name,sal��ֵ�������캯��Emp
				dm7.newEmp(emp);
				System.out.println("������Ա��" + name + "�ɹ�!");
			} else if (sel == 2) {
				System.out.println("��¼��Ա�����ţ�");
				String empNo = sr.next();
				// int index=
				dm7.showInfo(empNo);
			} else if (sel == 3) {
				System.out.println("��˾����Ա����Ϣ���£�");
				dm7.selectAllEmp();
			} else if (sel == 4) {
				System.out.println("�����빤�ţ�");
				String empNo = sr.next();
				System.out.println("�������޸�Ϊ��");
				float newSal = sr.nextFloat();
				// dm7.updateSal(empNo, newSal);
			} else if (sel == 5) {
				System.out.println("������Ҫɾ����Ա�Ĺ��ţ�");
				String empNo = sr.next();

				dm7.delEmp(dm7.getEmp(empNo));
			} else if (sel == 6) {
				System.out.println("�Ѱ�н�ʸߵͽ����������£�");
				// dm7.SortSal();
			} else if (sel == 7) {
				System.out.println("��ʾƽ�����ʼ���ߡ���͹�����Ա��Ϣ���£�");
				// dm7.Average();
			} else if (sel == 0) {
				System.out.println("�������˳�!");
				System.exit(0);
			} else {
				System.out.println("�����������������!");
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
			System.out.println("��ѯ���д���");
			System.exit(1);
		}
		return index;
	}

	/**
	 * ����������Ա������
	 * 
	 * @param emp
	 */
	public void newEmp(Employer emp) {
		try {
			emplist.add(emp);
			System.out.println("������Ա���ɹ�!," + emp.empName + " ��ӭ���ļ���!");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("���Ա�����������˳�");
			System.exit(1);
		}
	}

	/**
	 * ɾ��Ա������
	 * 
	 * @param emp
	 */
	public void delEmp(Employer emp) {

		try {
			emplist.remove(emp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("ɾ��Ա�����������˳�");
			System.exit(1);
		}
	}

	/**
	 * ��ѯԱ����Ϣ
	 * 
	 * @param emp
	 */
	public void selectEmp(Employer emp) {
		try {
			System.out.println("Ա�������ǣ�" + emp.empNo + ",������" + emp.empName
					+ ",н���ǣ�" + emp.empSal);

		} catch (Exception e) {
			// TODO: handle exception

		}
	}

	/**
	 * ��ѯ����Ա����Ϣ
	 */
	public void selectAllEmp() {
		Employer emp = null;
		for (int i = 0; i < emplist.size(); i++) {
			emp = emplist.get(i);
			try {
				System.out.println("Ա�������ǣ�" + emp.empNo + ",������" + emp.empName
						+ ",н���ǣ�" + emp.empSal);

			} catch (Exception e) {
				// TODO: handle exception

			}
		}
	}

	/**
	 * ����
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

	// ����Ա��������ʾԱ���������Ϣ
	public void showInfo(String empNo) {// �����Ŵ���showInfo������
		// ��������ArrayList
		for (int i = 0; i < emplist.size(); i++) {
			// ȡ��Emp����
			Employer emp = (Employer) emplist.get(i);
			// �Ƚϱ��
			if (emp.getEmpNo().equals(empNo)) {// ����empNo����ΪString������Ҫʹ��equals�������ݱȽϣ�������ʹ��==��ַ�Ƚ�
				System.out.println("�ҵ���Ա����������Ϣ�ǣ�");
				System.out.println("����:" + empNo + "\t�պ�:" + emp.getEmpName()
						+ "\tнˮ:" + emp.getEmpSal());
			} else {
				System.out.println("���Ų����ڻ��޴���!");
			}
		}
	}
}
