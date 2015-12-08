/**
 * 
 */
package jimmy.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

import jimmy.commons.Log4j;
import jimmy.conn.MysqlTest;

import org.apache.logging.log4j.Logger;

/**
 * @author JH
 *
 */
public class testmain {

	/**
	 * @param args
	 */
	static Log4j log4j = new Log4j();
	static Logger logger = log4j.getLogger(testmain.class.getName());

	public static void main(String[] args) throws SQLException {
		//
		testmain tm = new testmain();
		tm.test3();

	}

	// �ַ����Ĳ���

	// �õ����ڸ�ʽ����
	// Date date = fmt.parse(strDateMake);
	public static void Calen() {
		logger.debug("������ʾ����ʱ�䣺");
		// �ַ���ת�����ڸ�ʽ
		DateFormat fdate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String str = fdate.format(new Date());
		logger.debug(str);

		// ���� Calendar ����
		Calendar calendar = Calendar.getInstance();
		// ��ʼ�� Calendar ���󣬵�������Ҫ��������Ҫ����ʱ��

		calendar.setTime(new Date());

		// ��ʾ���
		logger.debug("�꣺ " + calendar.get(Calendar.YEAR));

		// ��ʾ�·� (��0��ʼ, ʵ����ʾҪ��һ)
		logger.debug("�£� " + calendar.get(Calendar.MONTH));

		// ��ǰ������
		logger.debug("���ӣ� " + calendar.get(Calendar.MINUTE));

		// ����ĵ� N ��
		logger.debug("����ĵ� " + calendar.get(Calendar.DAY_OF_YEAR) + "��");

		// ���µ� N ��
		logger.debug("���µĵ� " + calendar.get(Calendar.DAY_OF_MONTH) + "��");

		// 3Сʱ�Ժ�
		calendar.add(Calendar.HOUR_OF_DAY, 3);
		logger.debug("��Сʱ�Ժ��ʱ�䣺 " + calendar.getTime());
		// ��ʽ����ʾ
		str = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SS")).format(calendar
				.getTime());
		logger.debug(str);

		// ���� Calendar ��ʾ��ǰʱ��
		calendar.setTime(new Date());
		str = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SS")).format(calendar
				.getTime());
		logger.debug(str);

		// ����һ�� Calendar ���ڱȽ�ʱ��
		Calendar calendarNew = Calendar.getInstance();

		// �趨Ϊ 5 Сʱ��ǰ�����ߴ���ʾ -1
		calendarNew.add(Calendar.HOUR, -5);
		logger.debug("ʱ��Ƚϣ�" + calendarNew.compareTo(calendar));

		// �趨7Сʱ�Ժ�ǰ�ߴ���ʾ 1
		calendarNew.add(Calendar.HOUR, +7);
		logger.debug("ʱ��Ƚϣ�" + calendarNew.compareTo(calendar));

		// �˻� 2 Сʱ��ʱ����ͬ����ʾ 0
		calendarNew.add(Calendar.HOUR, -2);
		logger.debug("ʱ��Ƚϣ�" + calendarNew.compareTo(calendar));
	}

	public static void getRan() {
		Random rd = new Random(47);
		logger.debug("GET INT :" + rd.nextInt(100));
		logger.debug("GET DOUBLE :" + rd.nextDouble());
		logger.debug("GET FLOAT :" + rd.nextFloat());
		logger.debug("GET GAUSSIAN :" + rd.nextGaussian());
	}

	public void mysqltest() {
		MysqlTest Mysqltest = new MysqlTest();
		String sql = null;
		Connection conn = null;
		conn = Mysqltest.GetOracleConn();
		// Mysqltest.insert(sql, conn);
		// /conn=Mysqltest.GetConn();
		// Mysqltest.select(sql,conn);
		// conn=Mysqltest.GetConn();
		// Mysqltest.delete(sql, conn);
		// conn=Mysqltest.GetConn();
		// Mysqltest.updaterow(sql, conn);
		try {
			// Mysqltest.initMysql(conn);
			Mysqltest.initOracle(conn);
			Mysqltest.initData(100, conn);
			Mysqltest.QueryOne4Update(conn);
			Mysqltest.QueryMany4Update(conn);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			Mysqltest.closeconn(conn);
		}
	}

	public static void j2se() {
		j2se book;
		String str = "xiaoxiede";
		String upstr = null;
		try {
			book = new j2se("This is my 1st book", "Peter", "1234567890");
			// // ����Book�����趨���������
			// logger.info("Book Name: " + book.getName());
			// // ����Ȿ�������
			// logger.info("Book Author: " + book.getAuthor());
			// // ����Ȿ�������
			// logger.info("ISBN: " + book.getISBN());
			// // ����Ȿ��ı���
			// upstr=book.toupper(str);
			// logger.info(upstr);
			// book.getlist();
			// book.getmap();
			// logger.info("The natural logarithm is " + Math.E);
			// logger.info("The circumference ratio is " + Math.PI);
			//
			book.generateLottery("3");
			logger.info(10 / 0);
		} catch (ArithmeticException a) {
			logger.info("this is an tet ");
			a.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void getEnum() {

		for (Week week : Week.values()) {
			logger.info("The order of " + week + " is " + week.ordinal());
			// ʹ��ordinal()�����鿴ö��Ԫ�ص�˳��

			logger.info("Compare to MONDAY : " + week.compareTo(Week.MONDAY));
			// ��compareTo()����������ǰö��Ԫ����Week.MONDAY�Ƚ�

			logger.info("Equal to MONDAY ? " + week.equals(Week.MONDAY));
			// ��equals()����������ǰö��Ԫ����Week.MONDAY�Ƚ�

			logger.info("Equal to MONDAY by == ? " + (week == Week.MONDAY));
			// �ñȽ������==������ǰö��Ԫ����Week.MONDAY�Ƚ�
			// ��������Ӧ�ÿ��Կ���������ֱ��ʹ��==���Ƚ�����ö��Ԫ��

			logger.info("Name : " + week.name());
			// ʹ��name()�������ö��Ԫ�ص�����

			logger.info("Abbreviation : " + week.getAbbr());
			// ʹ���Զ����getAbbr()����������ڵ���д

			logger.info("-------------------");
			// ��ÿ��ѭ����Ҫ����ʱ����ӡһ���ָ�����������
		}
	}

	public void test1() {
		MysqlTest Mysqltest = new MysqlTest();
		// Connection conn=Mysqltest.GetMysqlConn();
		// Mysqltest.java_sql_time();
		// Mysqltest.autocommit(conn);
		// j2se();
		// TODO Auto-generated method stub
		Runnable tsmain = (Runnable) new Tread();
		Thread t0 = new Thread(tsmain);
		Thread t1 = new Thread(tsmain);
		Thread t2 = new Thread(tsmain);
		Thread t3 = new Thread(tsmain);
		Thread t4 = new Thread(tsmain);
		Thread t5 = new Thread(tsmain);
		Thread t6 = new Thread(tsmain);
		Thread t7 = new Thread(tsmain);
		Thread t8 = new Thread(tsmain);
		Thread t9 = new Thread(tsmain);
		t0.start();
		t1.start();
		try {
			t1.sleep(3);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		t2.start();
		t3.start();
		t4.start();
		t5.start();
		t6.start();
		t7.start();
		t8.start();
		t9.start();

		// logger.trace("this is the test message");
		// Date objDate = new Date();
		// logger.info("Please input your name :");
		// Scanner scan=new Scanner(System.in);
		//
		// String getin=scan.nextLine();
		// logger.info("this is your input:"+getin);
		InputOutput ioput = new InputOutput();
		String filename = "logs/2.txt";
		try {
			ioput.writeFileByLine(filename);
			ioput.readfile(filename);
			ioput.readFileByLine(filename);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		getEnum();
		// logger.trace(objDate.getTime());
		// logger.trace("TODAYIS "+objDate.getYear());
		// Calen();
		// getRan();
		// InputOutput IOput =new InputOutput();
		// try {
		// //IOput.getIOput();
		// IOput.getSeqOutput();
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// logger.error("IOput ���ִ���");
		// e.printStackTrace();
		// }
		// FileDemo filedemo=new FileDemo();

		// filedemo.getfile();
		// filedemo.randomfile();
		// fanxing fxdemo=new fanxing();

	}

	public void test2() {
		Scanner sc = new Scanner(System.in);
		String num1 = "";
		String num2 = "";
		int in3;
		num1 = sc.nextLine();

		System.out.println(num1);
		num2 = sc.nextLine();
		System.out.println(num2);

		boolean flag = true;
		while (flag) {
			System.out.println("���������֣�");
			String number = sc.next();
			Integer answer = null;
			try {
				answer = Integer.parseInt(number);
			} catch (NumberFormatException e) {
				System.out.println("�����������������");
				sc.nextLine();
				continue;
			}
			if (answer != null) {
				System.out.println("���Խ���");
				flag = false;
			} else {
				sc.nextLine();
			}
		}
		// int lay = 5;
		// for (int i = 1; i <= lay; i++) {
		// for (int f = 0; f < lay - i; f++) {
		// System.out.print(" ");
		// }
		// for (int j = 1; j <= (i * 2) - 1; j++) {
		//
		// System.out.print("#");
		// }
		// System.out.println();
		// }
		//
		// // int lay=10;//��ʾ�ж��ٲ�
		// for (int i = 1; i <= lay; i++) {
		// // �ҳ�����
		// // 1->3 2->2 3->1 4->0�ҳ��ո�
		// for (int k = 1; k <= lay - i; k++) {
		// System.out.print(" ");
		// }
		// // ��ӡ*
		// // 1->1 2->3 3->5 4->7�ҳ��ǵĹ���
		// for (int j = 1; j <= (i - 1) * 2 + 1; j++) {
		// System.out.print("*");
		// }
		// System.out.println();// ����
		// }
		System.exit(0);
	}

	public void test3() {
		try {
			InputStreamReader insr = new InputStreamReader(System.in);
			BufferedReader bfr = new BufferedReader(insr);
			System.out.println("����1-9,0�˳�");
			String num = bfr.readLine();

			int a1 = Integer.parseInt(num);
			for (int i = 1; i <= a1; i++) {
				for (int j = 1; j <= i; j++) {
					System.out.print(i + "X" + j + "=" + (i * j) + " ");
				}
				System.out.println();
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ERROR");
		}
	}
}