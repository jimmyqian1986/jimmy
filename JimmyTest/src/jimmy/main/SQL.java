package jimmy.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.Logger;

import jimmy.commons.Log4j;
public class SQL {
	private Connection conn = null;
	PreparedStatement statement = null;
    static Log4j log4j= new Log4j();
    static Logger logger=log4j.getLogger(SQL.class.getName());
	// connect to MySQL
	void connSQL() {
		String url = "jdbc:mysql://192.168.1.20:3306/test?characterEncoding=UTF-8";
		String username = "test";
		String password = "111111"; // ���������������������ݿ� 
		try { 
			Class.forName("com.mysql.jdbc.Driver" ); 
			conn = DriverManager.getConnection( url,username, password ); 
			}
		//����������������쳣
		 catch ( ClassNotFoundException cnfex ) {
			 logger.error(
			 "װ�� JDBC/ODBC ��������ʧ�ܡ�" );
			 cnfex.printStackTrace(); 
		 } 
		 //�����������ݿ��쳣
		 catch ( SQLException sqlex ) {
			 logger.error( "�޷��������ݿ�" );
			 sqlex.printStackTrace(); 
		 }
	}

	// disconnect to MySQL
	void deconnSQL() {
		try {
			if (conn != null)
				conn.close();
		} catch (Exception e) {
			logger.info("�ر����ݿ����� ��");
			e.printStackTrace();
		}
	}

	// execute selection language
	ResultSet selectSQL(String sql) {
		ResultSet rs = null;
		try {
			statement = conn.prepareStatement(sql);
			rs = statement.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	// execute insertion language
	boolean insertSQL(String sql) {
		try {
			statement = conn.prepareStatement(sql);
			statement.executeUpdate();
			return true;
		} catch (SQLException e) {
			logger.info("�������ݿ�ʱ����");
			e.printStackTrace();
		} catch (Exception e) {
			logger.info("����ʱ����");
			e.printStackTrace();
		}
		return false;
	}
	//execute delete language
	boolean deleteSQL(String sql) {
		try {
			statement = conn.prepareStatement(sql);
			statement.executeUpdate();
			return true;
		} catch (SQLException e) {
			logger.info("�������ݿ�ʱ����");
			e.printStackTrace();
		} catch (Exception e) {
			logger.info("����ʱ����");
			e.printStackTrace();
		}
		return false;
	}
	//execute update language
	boolean updateSQL(String sql) {
		try {
			statement = conn.prepareStatement(sql);
			statement.executeUpdate();
			return true;
		} catch (SQLException e) {
			logger.info("�������ݿ�ʱ����");
			e.printStackTrace();
		} catch (Exception e) {
			logger.info("����ʱ����");
			e.printStackTrace();
		}
		return false;
	}
	// show data in ju_users
	void layoutStyle2(ResultSet rs) {
		logger.info("-----------------");
		logger.info("ִ�н��������ʾ:");
		logger.info("-----------------");
		logger.info(" �û�ID" + "/t/t" + "�Ա�ID" + "/t/t" + "�û���"+ "/t/t" + "����");
		logger.info("-----------------");
		try {
			while (rs.next()) {
				logger.info(rs.getInt("userID") + "/t/t"
						+ rs.getString("taobaoID") + "/t/t"
						+ rs.getString("userName")
						 + "/t/t"+ rs.getString("userPWD"));
			}
		} catch (SQLException e) {
			logger.info("��ʾʱ���ݿ����");
			e.printStackTrace();
		} catch (Exception e) {
			logger.info("��ʾ����");
			e.printStackTrace();
		}
	}

	public static void main(String args[]) {

		SQL h = new SQL();
		h.connSQL();
		String s = "select * from users";

		String insert = "insert into users(userID,TaobaoID,userName,userPWD) values("+8329+","+34243+",'mm','789')";
		String update = "update users set userPWD =123 where userName= 'mm'";
		String delete = "delete from users where userName= 'mm'";

		if (h.insertSQL(insert) == true) {
			logger.info("insert successfully");
			ResultSet resultSet = h.selectSQL(s);
			h.layoutStyle2(resultSet);
		}
		if (h.updateSQL(update) == true) {
			logger.info("update successfully");
			ResultSet resultSet = h.selectSQL(s);	
			h.layoutStyle2(resultSet);
		}
		if (h.insertSQL(delete) == true) {
			logger.info("delete successfully");
			ResultSet resultSet = h.selectSQL(s);
			h.layoutStyle2(resultSet);
		}
		
		h.deconnSQL();
	}
}
