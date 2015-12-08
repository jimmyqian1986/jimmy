/**
 * 
 */
package jimmy.conn;
import org.apache.commons.logging.Log; 
import org.apache.commons.logging.LogFactory; 

import java.sql.*; 
import java.util.List; 
import java.util.Properties; 
/**
 * @author jimmy
 *
 */
public class DbConn {
	

	/** 
	* 通用数据库操作工具，提供数据库连接获取、SQL执行、资源关闭等功能，支持的数据库为Oracle10g、MySQL5.x。</P> 
	* 
	* @author leizhimin 2012-03-05 11:22 
	*/ 
 
	        private static Log log = LogFactory.getLog(DbConn.class); 

	        static { 
	                try { 
	                        Class.forName("oracle.jdbc.driver.OracleDriver"); 
	                        Class.forName("com.mysql.jdbc.Driver"); 
	                } catch (ClassNotFoundException e) { 
	                        log.error("加载数据库驱动发生错误！"); 
	                        e.printStackTrace(); 
	                } 
	        } 

	        /** 
	         * 创建一个数据库连接 
	         * 
	         * @param url                数据库连接URL串 
	         * @param properties 作为连接参数的任意字符串标记/值对的列表；通常至少应该包括 "user" 和 "password" 属性 
	         * @return 一个JDBC的数据库连接 
	         * @throws SQLException 获取连接失败时候抛出 
	         */ 
	        public static Connection makeConnection(String url, Properties properties) throws SQLException { 
	                Connection conn = null; 
	                try { 
	                        conn = DriverManager.getConnection(url, properties); 
	                } catch (SQLException e) { 
	                        log.error("获取数据库连接发生异常", e); 
	                        throw e; 
	                } 
	                return conn; 
	        } 

	        /** 
	         * 在一个数据库连接上执行一个静态SQL语句查询 
	         * 
	         * @param conn            数据库连接 
	         * @param staticSql 静态SQL语句字符串 
	         * @return 返回查询结果集ResultSet对象 
	         * @throws SQLException 执行异常时候抛出 
	         */ 
	        public static ResultSet executeQuery(Connection conn, String staticSql) throws SQLException { 
	                ResultSet rs = null; 
	                try { 
	                        //创建执行SQL的对象 
	                        Statement stmt = conn.createStatement(); 
	                        //执行SQL，并获取返回结果 
	                        rs = stmt.executeQuery(staticSql); 
	                } catch (SQLException e) { 
	                        log.error("执行SQL语句出错，请检查！\n" + staticSql); 
	                        throw e; 
	                } 
	                return rs; 
	        } 

	        /** 
	         * 在一个数据库连接上执行一个静态SQL语句 
	         * 
	         * @param conn            数据库连接 
	         * @param staticSql 静态SQL语句字符串 
	         * @throws SQLException 执行异常时候抛出 
	         */ 
	        public static void executeSQL(Connection conn, String staticSql) throws SQLException { 
	                Statement stmt = null; 
	                try { 
	                        //创建执行SQL的对象 
	                        stmt = conn.createStatement(); 
	                        //执行SQL，并获取返回结果 
	                        stmt.execute(staticSql); 
	                } catch (SQLException e) { 
	                        log.error("执行SQL语句出错，请检查！\n" + staticSql); 
	                        throw e; 
	                } finally { 
	                        close(stmt); 
	                } 
	        } 

	        /** 
	         * 在一个数据库连接上执行一批静态SQL语句 
	         * 
	         * @param conn        数据库连接 
	         * @param sqlList 静态SQL语句字符串集合 
	         * @throws SQLException 执行异常时候抛出 
	         */ 
	        public static void executeBatchSQL(Connection conn, List<String> sqlList) throws SQLException { 
	                try { 
	                        //创建执行SQL的对象 
	                        Statement stmt = conn.createStatement(); 
	                        for (String sql : sqlList) { 
	                                stmt.addBatch(sql); 
	                        } 
	                        //执行SQL，并获取返回结果 
	                        stmt.executeBatch(); 
	                } catch (SQLException e) { 
	                        log.error("执行批量SQL语句出错，请检查！"); 
	                        throw e; 
	                } 
	        } 

	        /** 
	         * 获取Oracle数据一个指定的Sequence下一个值 
	         * 
	         * @param conn         数据库连接 
	         * @param seq_name Sequence名称 
	         * @return Sequence下一个值 
	         */ 
	        public static long sequenceNextval(Connection conn, String seq_name) { 
	                long val = -1L; 
	                Statement stmt = null; 
	                ResultSet rs = null; 
	                try { 
	                        //创建执行SQL的对象 
	                        stmt = conn.createStatement(); 
	                        //执行SQL，并获取返回结果 
	                        rs = stmt.executeQuery("select " + seq_name + ".nextval from dual"); 
	                        if (rs.next()) val = rs.getLong(1); 
	                } catch (SQLException e) { 
	                        log.error("#ERROR# :获取Sequence值出错，请检查！\n" + seq_name); 
	                        e.printStackTrace(); 
	                        throw new RuntimeException(e); 
	                } finally { 
	                        close(rs); 
	                        close(stmt); 
	                } 
	                return val; 
	        } 

	        /** 
	         * 关闭所有可关闭的JDBC资源，不论先后顺序，总能以正确的顺序执行 
	         * 
	         * @param objs 可关闭的资源对象有Connection、Statement、ResultSet，别的类型资源自动忽略 
	         */ 
	        public static void closeAll(Object... objs) { 
	                for (Object obj : objs) 
	                        if (obj instanceof ResultSet) close((ResultSet) obj); 
	                for (Object obj : objs) 
	                        if (obj instanceof Statement) close((Statement) obj); 
	                for (Object obj : objs) 
	                        if (obj instanceof Connection) close((Connection) obj); 
	        } 

	        private static void close(Connection conn) { 
	                if (conn != null) 
	                        try { 
	                                conn.close(); 
	                        } catch (SQLException e) { 
	                                log.error("关闭数据库连接发生异常！"); 
	                        } 
	        } 

	        private static void close(ResultSet rs) { 
	                if (rs != null) 
	                        try { 
	                                rs.close(); 
	                        } catch (SQLException e) { 
	                                log.error("关闭结果集发生异常！"); 
	                        } 
	        } 

	        private static void close(Statement stmt) { 
	                if (stmt != null) 
	                        try { 
	                                stmt.close(); 
	                        } catch (SQLException e) { 
	                                log.error("关闭SQL语句发生异常！"); 
	                        } 
	        } 

	        /** 
	         * 测试代码，没用 
	         * 
	         * @param args 
	         * @throws SQLException 
	         */ 
	        public static void main(String[] args) throws SQLException { 
	                String tns = "jdbc:oracle:thin:@\n" + 
	                                "(description= \n" + 
	                                "\t(ADDRESS_LIST =\n" + 
	                                "\t\t(address=(protocol=tcp)(host=192.168.1.20)(port=1521))\n" + 
	                                "\t\t(address=(protocol=tcp)(host=192.168.1.20)(port=1521))\n" + 
	                                "\t\t(address=(protocol=tcp)(host=192.168.1.20)(port=1521))\n" + 
	                                "\t\t(load_balance=yes)\n" + 
	                                "\t)\n" + 
	                                "\t(connect_data =\n" + 
	                                "\t\t(service_name=orcl)\n" + 
	                                "\t\t(failover_mode =\n" + 
	                                "\t\t\t(type=session)\n" + 
	                                "\t\t\t(method=basic)\n" + 
	                                "\t\t\t(retries=5)\n" + 
	                                "\t\t\t(delay=15)\n" + 
	                                "\t\t)\n" + 
	                                "\t)\n" + 
	                                ")"; 
	                Properties p_ora = new Properties(); 
	                p_ora.put("user", "test"); 
	                p_ora.put("password", "111111"); 
	                p_ora.put("internal_logon", "normal"); 

//	                Connection ora_conn = makeConnection(tns, p_ora); 
//	                ResultSet rs1 = ora_conn.createStatement().executeQuery("select count(1) from user_tables"); 
//	                rs1.next(); 
//	                System.out.println(rs1.getInt(1)); 
//	                rs1.close(); 
//	                ora_conn.close(); 

	                Properties p_mysql = new Properties(); 
	                p_mysql.put("user", "root"); 
	                p_mysql.put("password", "111111"); 
	                String url = "jdbc:mysql://192.168.1.20:3306/test"; 
	                Connection mysql_conn = makeConnection(url, p_mysql); 
	                ResultSet rs2 = mysql_conn.createStatement().executeQuery("select sysdate()"); 
	                rs2.next(); 
	                System.out.println(rs2.getDate(1)); 
	                rs2.close(); 
	                mysql_conn.close(); 
	        } 
	
}
