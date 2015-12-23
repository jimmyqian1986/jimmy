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
 * ��ʾʹ��jdbc-odbc������ʽ����sql server���ݿ� �������test���ݿ��emp���dept��
 * 1����������Դ--windows���ڿ������-->������-->ODBC����Դ-->�û�DSN--��� sql server 2���ڳ�����ȥ��������Դ
 * 3��ʹ��Statement(����ȫ)�������ݿ�
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
			// 1����������(����Ҫ��������������ڴ�)
			Class.forName("com.mysql.jdbc.Driver");
			// 2���õ�����(ָ�����ӵ��ĸ�����Դ�����ݿ���û���������)
			// �����������Դ��ʱ��ѡ�����windows NT��֤��ʽ������Ҫ���ݿ���û���������
			// Connection ct=DriverManager.getConnection("jdbc:odbc:mytest");
			ct = DriverManager.getConnection(
					"jdbc:mysql://192.168.1.20:3306/test", "test", "111111");
			// 3������Statement����PreparedStatement(����)
			// Statement�ô�����Ҫ���ڷ���SQL��䵽���ݿ�
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

			// 4��ִ��(CRUD���������ݿ⡢�������ݿ⡢ɾ�����ݿ�)
			// ��ʾ1�����һ�����ݵ�dept����
			// executeUpdate����ִ��CUD����(��ӡ�ɾ�����޸�)
			int i = sm
					.executeUpdate("insert into dept values('60','������','����')");
			if (i == 1) {
				logger.info("������ӳɹ�");
			} else {
				logger.info("���ʧ��");
			}

			// ��ʾ2����dept����ɾ��һ����¼
			int j = sm.executeUpdate("delete from dept where deptno='50'");
			if (j == 1) {
				logger.info("����ɾ���ɹ�");
			} else {
				logger.info("ɾ��ʧ��");
			}

			// ��ʾ3����dept�����޸�deptno=40 loc��Ϊbeijing
			int k = sm
					.executeUpdate("update dept set loc='beijing' where deptno='40'");
			if (k == 1) {
				logger.info("�����޸ĳɹ�");
			} else {
				logger.info("�޸�ʧ��");
			}

			// ��ʾ4����ѯ,��ʾ���еĲ�����Ϣ
			// ResultSet�����,��ҿ��԰�ResultSet���ɷ���һ�ű��еĽ����
			rs = sm.executeQuery("select * from dept");
			// ѭ��ȡ��
			while (rs.next()) {
				int a = rs.getInt("deptno");
				String b = rs.getString("deptname");
				String c = rs.getString("loc");
				logger.info(a + ":" + b + ":" + c);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// �ر���Դ���ر�˳���ȴ�����رգ��󴴽��ȹر�
			try {
				// Ϊ�˳���׳
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
			// 1����������(����Ҫ��������������ڴ�)
			Class.forName("com.mysql.jdbc.Driver");
			// 2���õ�����(ָ�����ӵ��ĸ�����Դ�����ݿ���û���������)
			// �����������Դ��ʱ��ѡ�����windows NT��֤��ʽ������Ҫ���ݿ���û���������
			// Connection ct=DriverManager.getConnection("jdbc:odbc:mytest");
			ct = DriverManager.getConnection(
					"jdbc:mysql://192.168.1.20:3306/test", "test", "111111");
			// 3������Statement����PreparedStatement(����)
			// Statement�ô�����Ҫ���ڷ���SQL��䵽���ݿ�
			// PreparedStatement�ô�����Ҫ���ڷ���SQL��䵽���ݿ�
			ps = ct.prepareStatement("select * from dept where deptno=? and loc=?");
			// //��?��ֵ(�ɷ�ֹSQLע��©������)����Ҫֱ��ʹ��ƴ�ӵķ�ʽ
			ps.setInt(1, 20);
			ps.setString(2, "dallas");
			// //��ʾ����ѯ,��ʾ���еĲ�����Ϣ
			// //ResultSet�����,��ҿ��԰�ResultSet���ɷ���һ�ű��еĽ����
			rs = ps.executeQuery();
			// //ѭ��ȡ��
			while (rs.next()) {
				int a = rs.getInt(1);
				String b = rs.getString(2);
				String c = rs.getString(3);
				logger.info(a + "\t" + b + "\t" + c);
			}

			// ʹ��PreparedStetement���һ����¼
			ps = ct.prepareStatement("insert into dept values(?,?,?)");
			ps.setInt(1, 60);
			ps.setString(2, "��ȫ��");
			ps.setString(3, "�Ϻ�");
			// ִ��
			int i = ps.executeUpdate();
			if (i == 1) {
				logger.info("��ӳɹ�");
			} else {
				logger.info("���ʧ��");
			}

			// ʹ��PreparedStetement�޸�һ����¼��dept�����޸�loc=�Ϻ� deptno��Ϊ50
			ps = ct.prepareStatement("update dept set deptno=? where loc='�Ϻ�'");
			ps.setInt(1, 50);
			// ִ��
			i = ps.executeUpdate();
			if (i == 1) {
				logger.info("�޸ĳɹ�");
			} else {
				logger.info("�޸�ʧ��");
			}

			// ʹ��PreparedStetementɾ��һ����¼
			ps = ct.prepareStatement("delete from dept where deptno=?");
			ps.setInt(1, 50);
			i = ps.executeUpdate();
			if (i == 1) {
				logger.info("ɾ���ɹ�");
			} else {
				logger.info("ɾ��ʧ��");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// �ر���Դ���ر�˳���ȴ�����رգ��󴴽��ȹر�
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