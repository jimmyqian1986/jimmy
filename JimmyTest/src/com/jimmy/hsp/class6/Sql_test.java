/**
 * 
 */
package com.jimmy.hsp.class6;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import jimmy.commons.Log4j;

import org.apache.logging.log4j.Logger;

/**
 * @author jimmy
 *
 */
/**
 * 演示使用jdbc-odbc桥连方式操作sql server数据库 具体操作test数据库的emp表和dept表
 * 1、配置数据源--windows下在控制面板-->管理工具-->ODBC数据源-->用户DSN--添加 sql server 2、在程序中去连接数据源
 * 3、使用Statement(不安全)连接数据库
 */
public class Sql_test {
	static Log4j log = new Log4j();
	static Logger logger = log.getLogger(Sql_test.class.getName());
	Connection ct = null;
	Statement sm = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	public static void main(String[] args) {
		Sql_test sql = new Sql_test();
		sql.stmt();
		sql.PreparedStmt();
	}

	public void stmt() {

		try {
			// 1、加载驱动(把需要的驱动程序加入内存)
			Class.forName("com.mysql.jdbc.Driver");
			// 2、得到连接(指定连接到哪个数据源、数据库的用户名和密码)
			// 如果配置数据源的时候选择的是windows NT验证方式，则不需要数据库的用户名和密码
			// Connection ct=DriverManager.getConnection("jdbc:odbc:mytest");
			ct = DriverManager.getConnection(
					"jdbc:mysql://192.168.1.20:3306/test", "test", "111111");
			// 3、创建Statement或者PreparedStatement(区别)
			// Statement用处：主要用于发送SQL语句到数据库
			sm = ct.createStatement();
			try {
				boolean drop_flag = sm.execute("drop table  if exists dept");
				if (drop_flag != true) {
					logger.error("drop table error");
				}
				boolean create_flag = sm
						.execute("create table dept(deptno int(3),deptname varchar(20),loc varchar(20))");
				if (create_flag != true) {
					logger.error("create table error");
				}
			} catch (Exception e) {
				logger.error("drop or create table Exception");
			}

			// 4、执行(CRUD，创建数据库、备份数据库、删除数据库)
			// 演示1：添加一条数据到dept表中
			// executeUpdate可以执行CUD操作(添加、删除、修改)
			int i = sm
					.executeUpdate("insert into dept values('60','保安部','西永')");
			if (i == 1) {
				logger.info("数据添加成功");
			} else {
				logger.info("添加失败");
			}

			// 演示2：从dept表中删除一条记录
			int j = sm.executeUpdate("delete from dept where deptno='50'");
			if (j == 1) {
				logger.info("数据删除成功");
			} else {
				logger.info("删除失败");
			}

			// 演示3：从dept表中修改deptno=40 loc改为beijing
			int k = sm
					.executeUpdate("update dept set loc='beijing' where deptno='40'");
			if (k == 1) {
				logger.info("数据修改成功");
			} else {
				logger.info("修改失败");
			}

			// 演示4：查询,显示所有的部门信息
			// ResultSet结果集,大家可以把ResultSet理解成返回一张表行的结果集
			rs = sm.executeQuery("select * from dept");
			// 循环取出
			while (rs.next()) {
				int a = rs.getInt("deptno");
				String b = rs.getString("deptname");
				String c = rs.getString("loc");
				logger.info(a + ":" + b + ":" + c);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 关闭资源，关闭顺序先创建后关闭，后创建先关闭
			try {
				// 为了程序健壮
				if (rs != null) {
					rs.close();
				}
				if (sm != null) {
					sm.close();
				}
				if (ct != null) {
					ct.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		this.PreparedStmt();
	}

	public void PreparedStmt() {
		try {
			// 1、加载驱动(把需要的驱动程序加入内存)
			Class.forName("com.mysql.jdbc.Driver");
			// 2、得到连接(指定连接到哪个数据源、数据库的用户名和密码)
			// 如果配置数据源的时候选择的是windows NT验证方式，则不需要数据库的用户名和密码
			// Connection ct=DriverManager.getConnection("jdbc:odbc:mytest");
			ct = DriverManager.getConnection(
					"jdbc:mysql://192.168.1.20:3306/test", "test", "111111");
			// 3、创建Statement或者PreparedStatement(区别)
			// Statement用处：主要用于发送SQL语句到数据库
			// PreparedStatement用处：主要用于发送SQL语句到数据库
			ps = ct.prepareStatement("select * from dept where deptno=? and loc=?");
			// //给?赋值(可防止SQL注入漏洞问题)，不要直接使用拼接的方式
			ps.setInt(1, 20);
			ps.setString(2, "dallas");
			// //演示：查询,显示所有的部门信息
			// //ResultSet结果集,大家可以把ResultSet理解成返回一张表行的结果集
			rs = ps.executeQuery();
			// //循环取出
			while (rs.next()) {
				int a = rs.getInt(1);
				String b = rs.getString(2);
				String c = rs.getString(3);
				logger.info(a + "\t" + b + "\t" + c);
			}

			// 使用PreparedStetement添加一条记录
			ps = ct.prepareStatement("insert into dept values(?,?,?)");
			ps.setInt(1, 60);
			ps.setString(2, "安全部");
			ps.setString(3, "上海");
			// 执行
			int i = ps.executeUpdate();
			if (i == 1) {
				logger.info("添加成功");
			} else {
				logger.info("添加失败");
			}

			// 使用PreparedStetement修改一条记录从dept表中修改loc=上海 deptno改为50
			ps = ct.prepareStatement("update dept set deptno=? where loc='上海'");
			ps.setInt(1, 50);
			// 执行
			i = ps.executeUpdate();
			if (i == 1) {
				logger.info("修改成功");
			} else {
				logger.info("修改失败");
			}

			// 使用PreparedStetement删除一条记录
			ps = ct.prepareStatement("delete from dept where deptno=?");
			ps.setInt(1, 50);
			i = ps.executeUpdate();
			if (i == 1) {
				logger.info("删除成功");
			} else {
				logger.info("删除失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 关闭资源，关闭顺序先创建后关闭，后创建先关闭
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (ct != null) {
					ct.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}
}