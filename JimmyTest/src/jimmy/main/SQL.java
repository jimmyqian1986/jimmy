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
		String password = "111111"; // 加载驱动程序以连接数据库 
		try { 
			Class.forName("com.mysql.jdbc.Driver" ); 
			conn = DriverManager.getConnection( url,username, password ); 
			}
		//捕获加载驱动程序异常
		 catch ( ClassNotFoundException cnfex ) {
			 logger.error(
			 "装载 JDBC/ODBC 驱动程序失败。" );
			 cnfex.printStackTrace(); 
		 } 
		 //捕获连接数据库异常
		 catch ( SQLException sqlex ) {
			 logger.error( "无法连接数据库" );
			 sqlex.printStackTrace(); 
		 }
	}

	// disconnect to MySQL
	void deconnSQL() {
		try {
			if (conn != null)
				conn.close();
		} catch (Exception e) {
			logger.info("关闭数据库问题 ：");
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
			logger.info("插入数据库时出错：");
			e.printStackTrace();
		} catch (Exception e) {
			logger.info("插入时出错：");
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
			logger.info("插入数据库时出错：");
			e.printStackTrace();
		} catch (Exception e) {
			logger.info("插入时出错：");
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
			logger.info("插入数据库时出错：");
			e.printStackTrace();
		} catch (Exception e) {
			logger.info("插入时出错：");
			e.printStackTrace();
		}
		return false;
	}
	// show data in ju_users
	void layoutStyle2(ResultSet rs) {
		logger.info("-----------------");
		logger.info("执行结果如下所示:");
		logger.info("-----------------");
		logger.info(" 用户ID" + "/t/t" + "淘宝ID" + "/t/t" + "用户名"+ "/t/t" + "密码");
		logger.info("-----------------");
		try {
			while (rs.next()) {
				logger.info(rs.getInt("userID") + "/t/t"
						+ rs.getString("taobaoID") + "/t/t"
						+ rs.getString("userName")
						 + "/t/t"+ rs.getString("userPWD"));
			}
		} catch (SQLException e) {
			logger.info("显示时数据库出错。");
			e.printStackTrace();
		} catch (Exception e) {
			logger.info("显示出错。");
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
