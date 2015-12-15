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

	// 字符流的测试

	// 得到日期格式对象
	// Date date = fmt.parse(strDateMake);
	public static void Calen() {
		logger.debug("完整显示日期时间：");
		// 字符串转换日期格式
		DateFormat fdate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String str = fdate.format(new Date());
		logger.debug(str);

		// 创建 Calendar 对象
		Calendar calendar = Calendar.getInstance();
		// 初始化 Calendar 对象，但并不必要，除非需要重置时间

		calendar.setTime(new Date());

		// 显示年份
		logger.debug("年： " + calendar.get(Calendar.YEAR));

		// 显示月份 (从0开始, 实际显示要加一)
		logger.debug("月： " + calendar.get(Calendar.MONTH));

		// 当前分钟数
		logger.debug("分钟： " + calendar.get(Calendar.MINUTE));

		// 今年的第 N 天
		logger.debug("今年的第 " + calendar.get(Calendar.DAY_OF_YEAR) + "天");

		// 本月第 N 天
		logger.debug("本月的第 " + calendar.get(Calendar.DAY_OF_MONTH) + "天");

		// 3小时以后
		calendar.add(Calendar.HOUR_OF_DAY, 3);
		logger.debug("三小时以后的时间： " + calendar.getTime());
		// 格式化显示
		str = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SS")).format(calendar
				.getTime());
		logger.debug(str);

		// 重置 Calendar 显示当前时间
		calendar.setTime(new Date());
		str = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SS")).format(calendar
				.getTime());
		logger.debug(str);

		// 创建一个 Calendar 用于比较时间
		Calendar calendarNew = Calendar.getInstance();

		// 设定为 5 小时以前，后者大，显示 -1
		calendarNew.add(Calendar.HOUR, -5);
		logger.debug("时间比较：" + calendarNew.compareTo(calendar));

		// 设定7小时以后，前者大，显示 1
		calendarNew.add(Calendar.HOUR, +7);
		logger.debug("时间比较：" + calendarNew.compareTo(calendar));

		// 退回 2 小时，时间相同，显示 0
		calendarNew.add(Calendar.HOUR, -2);
		logger.debug("时间比较：" + calendarNew.compareTo(calendar));
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
			// // 创建Book对象并设定其各个属性
			// logger.info("Book Name: " + book.getName());
			// // 输出这本书的名字
			// logger.info("Book Author: " + book.getAuthor());
			// // 输出这本书的作者
			// logger.info("ISBN: " + book.getISBN());
			// // 输出这本书的编码
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
			// 使用ordinal()方法查看枚举元素的顺序

			logger.info("Compare to MONDAY : " + week.compareTo(Week.MONDAY));
			// 用compareTo()方法来将当前枚举元素与Week.MONDAY比较

			logger.info("Equal to MONDAY ? " + week.equals(Week.MONDAY));
			// 用equals()方法来将当前枚举元素与Week.MONDAY比较

			logger.info("Equal to MONDAY by == ? " + (week == Week.MONDAY));
			// 用比较运算符==来将当前枚举元素与Week.MONDAY比较
			// 从这里你应该可以看到，可以直接使用==来比较两个枚举元素

			logger.info("Name : " + week.name());
			// 使用name()方法获得枚举元素的名称

			logger.info("Abbreviation : " + week.getAbbr());
			// 使用自定义的getAbbr()方法输出星期的缩写

			logger.info("-------------------");
			// 在每次循环快要结束时，打印一条分割线用于区分
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
		// logger.error("IOput 出现错误");
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
			System.out.println("请输入数字：");
			String number = sc.next();
			Integer answer = null;
			try {
				answer = Integer.parseInt(number);
			} catch (NumberFormatException e) {
				System.out.println("输入错误，请重新输入");
				sc.nextLine();
				continue;
			}
			if (answer != null) {
				System.out.println("测试结束");
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
		// // int lay=10;//表示有多少层
		// for (int i = 1; i <= lay; i++) {
		// // 找出规律
		// // 1->3 2->2 3->1 4->0找出空格
		// for (int k = 1; k <= lay - i; k++) {
		// System.out.print(" ");
		// }
		// // 打印*
		// // 1->1 2->3 3->5 4->7找出星的规律
		// for (int j = 1; j <= (i - 1) * 2 + 1; j++) {
		// System.out.print("*");
		// }
		// System.out.println();// 换行
		// }
		System.exit(0);
	}

	public void test3() {
		try {
			InputStreamReader insr = new InputStreamReader(System.in);
			BufferedReader bfr = new BufferedReader(insr);
			System.out.println("输入1-9,0退出");
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