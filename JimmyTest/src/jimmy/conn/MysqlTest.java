/**
 * 
 */
package jimmy.conn;

/**
 * @author JH
 *
 */

import java.sql.*;

import jimmy.commons.Log4j;

import org.apache.logging.log4j.Logger;

public class MysqlTest {
	// JDBC 驱动器的名称和数据库地址
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://192.168.1.20/test";

	static final String USER = "test";
	static final String PASS = "111111";
	static Log4j log4j = new Log4j();
	static Logger logger = log4j.getLogger(MysqlTest.class.getName());

	public void GetConnection() {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {

			conn = GetMysqlConn();

			// 执行查询
			logger.info("Creating statement...");
			// 这里我们要更改一个同学的年龄，参数待定
			String sql = "UPDATE t_user set age=? WHERE id=?";
			stmt = conn.prepareStatement(sql);

			// 将值绑定到参数，参数从左至右序号为1，2...
			stmt.setInt(1, 200); // 绑定 age 的值(序号为1)
			stmt.setInt(2, 1); // 绑定 ID 的值

			// 更新 ID 为1的同学的年龄
			int rows = stmt.executeUpdate();
			logger.info("被影响的行数 : " + rows);
			stmt.setInt(1, 300);
			stmt.setInt(2, 2);
			int rows2 = stmt.executeUpdate();
			logger.info("被影响的行数2 : " + rows2);
			// 查询所有记录，并显示.
			sql = "SELECT id, name, age FROM t_user";
			ResultSet rs = stmt.executeQuery(sql);

			// 处理结果集
			while (rs.next()) {
				// 检索
				int id = rs.getInt("id");
				int age = rs.getInt("age");
				String name = rs.getString("name");

				// 显示
				logger.info("ID: " + id + ", Age: " + age + ", Name: " + name);
				logger.info("--------------------");
			}
			// 清理
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		logger.info("Goodbye!");
	}

	public void select(String sql, Connection conn) {
		Statement stmt = null;

		try {
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			String selectsql;
			selectsql = "SELECT id, name, age FROM t_user";
			ResultSet rs = stmt.executeQuery(selectsql);

			// 将光标移到最后一行
			logger.info("Moving cursor to the last...");
			rs.last();

			// 处理结果集
			logger.info("Displaying record...");
			int id = rs.getInt(1);
			int age = rs.getInt("age");
			String name = rs.getString("name");

			// 显示
			logger.info("ID: " + id + ", Age: " + age + ", Name: " + name);
			logger.info("-------------------");

			// 将光标移到第一行
			logger.info("Moving cursor to the first row...");
			rs.first();

			logger.info("Displaying record...");
			id = rs.getInt(1);
			age = rs.getInt("age");
			name = rs.getString("name");

			// 显示
			logger.info("ID: " + id + ", Age: " + age + ", Name: " + name);

			// 将光标移至下一行
			logger.info("Moving cursor to the next row...");
			while (rs.next()) {

				logger.info("Displaying record...");
				id = rs.getInt(1);
				age = rs.getInt("age");
				name = rs.getString("name");

				// 显示
				logger.info("ID: " + id + ", Age: " + age + ", Name: " + name);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		logger.info("Goodbye!");
	}

	public  Connection GetMysqlConn() {
		Connection conn = null;
		// 注册JDBC 驱动程序
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 打开连接
		logger.info("Connecting to database...");
		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info("Creating statement...");
		return conn;

	}

	public void insert(String sql, Connection conn) {
		String InsertSql;
		boolean insert;
		InsertSql = "INSERT INTO T_USER values (?,?,?)";
		PreparedStatement stmtinsert = null;
		try {
			stmtinsert = conn.prepareStatement(InsertSql);

			for (int i = 1; i < 100; i++) {
				stmtinsert.setInt(1, i);
				stmtinsert.setInt(3, i);
				stmtinsert.setString(2, "tony");
				insert = stmtinsert.execute();
				if (insert = false) {
					logger.info("INSERT SQL ERROR:I =" + i);
				} else {
					logger.info("INSERT SQL SUCCES:I=" + i);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (stmtinsert != null)
					stmtinsert.close();
			} catch (SQLException se2) {
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}

	}

	public void delete(String sql, Connection conn) {
		String DeleteSql;

		DeleteSql = "delete from t_user where id=?";
		PreparedStatement stmtinsert = null;
		try {
			boolean insert;
			stmtinsert = conn.prepareStatement(DeleteSql);

			for (int i = 1; i < 100; i++) {
				stmtinsert.setInt(1, i);
				// stmtinsert.setInt(3, i);
				// stmtinsert.setString(2, "tony");
				insert = stmtinsert.execute();
				if (insert = false) {
					logger.info("DELETE SQL ERROR:I =" + i);
				} else {
					logger.info("DELETE SQL SUCCES:I=" + i);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (stmtinsert != null)
					stmtinsert.close();
			} catch (SQLException se2) {
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}

	}

	/**
	 * @return
	 * 
	 */
	public void insertrow(String sql, Connection conn) {

		String selectsql = "SELECT id, name, age FROM t_user";
		ResultSet rs;
		try {
			Statement stmt = conn.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			rs = stmt.executeQuery(selectsql);

			// 结果集中插入新行
			rs.moveToInsertRow();
			rs.updateInt("id", 500);
			rs.updateString("name", "John");
			rs.updateInt("age", 21);
			// 更新数据库
			rs.insertRow();
			logger.info("INSSERT FROM RESULTSET ");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void updaterow(String sql, Connection conn) {
		String selectsql = "SELECT id, name, age FROM t_user where id=500";
		ResultSet rs;
		try {
			Statement stmt = conn.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			rs = stmt.executeQuery(selectsql);

			// 结果集中插入新行
			// rs.updateRow();
			// rs.updateInt("id",500);
			rs.updateString("name", "jackchen");
			// rs.updateInt("age",210);
			// 更新数据库
			rs.updateRow();
			logger.info("UPDATE FROM RESULTSET ");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * JDBC批量Update深度优化
	 * 
	 * @author leizhimin 2009-7-30 8:38:33
	 */

	/**
	 * 初始化测试环境
	 * 
	 * @throws SQLException
	 *             异常时抛出
	 */
	public void initMysql(Connection conn) throws SQLException {
		//Connection conn = GetConn();
		conn.setAutoCommit(false);
		Statement stmt = conn.createStatement();
		try {
			stmt.addBatch("DROP TABLE IF EXISTS tuser");
			stmt.addBatch("CREATE TABLE tuser (\n"
					+ "    id bigint(20) NOT NULL AUTO_INCREMENT,\n"
					+ "    name varchar(12) DEFAULT NULL,\n"
					+ "    remark varchar(24) DEFAULT NULL,\n"
					+ "    createtime datetime DEFAULT NULL,\n"
					+ "    updatetime datetime DEFAULT NULL,\n"
					+ "    PRIMARY KEY (id)\n"
					+ ") ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8");
			stmt.executeBatch();
			conn.commit();
			logger.info("--------数据库所支持的ResultSet的类型---------");
			logger.info("ResultSet.TYPE_FORWARD_ONLY\t\t\t:"
					+ conn.getMetaData().supportsResultSetType(
							ResultSet.TYPE_FORWARD_ONLY));
			logger.info("ResultSet.TYPE_SCROLL_INSENSITIVE\t:"
					+ conn.getMetaData().supportsResultSetType(
							ResultSet.TYPE_SCROLL_INSENSITIVE));
			logger.info("ResultSet.TYPE_SCROLL_SENSITIVE\t\t:"
					+ conn.getMetaData().supportsResultSetType(
							ResultSet.TYPE_SCROLL_SENSITIVE));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	public void initOracle(Connection conn) throws SQLException {
		//Connection conn = GetConn();
		conn.setAutoCommit(false);
		Statement stmt = conn.createStatement();
		try {
			stmt.addBatch("DROP TABLE  tuser");
			stmt.addBatch("CREATE TABLE tuser (\n"
					+ "    id  number  not null,\n"
					+ "    name varchar2(12) ,\n"
					+ "    remark varchar2(24) ,\n"
					+ "    createtime date ,\n"
					+ "    updatetime date ,\n"
					+ "    PRIMARY KEY (id)\n"
					+ ") ");
			stmt.executeBatch();
			stmt.addBatch("create or replace trigger tri_tuser\n"
			+ "  before insert \n"
			+ "  on tuser \n"
			+ "  for each row \n"
			+ " declare \n"
			+ "  -- local variables here \n"
			+ "  id number; \n"
		+ "	begin \n"
			+ "  select nvl(max(id),0)+1 into id from tuser; \n"
		+ "	  :new.id:=id; \n"
		+ "	end tri_tuser;");
			
			stmt.addBatch("alter trigger tri_tuser enable");
			stmt.executeBatch();
			conn.commit();
			logger.info("--------数据库所支持的ResultSet的类型---------");
			logger.info("ResultSet.TYPE_FORWARD_ONLY\t\t\t:"
					+ conn.getMetaData().supportsResultSetType(
							ResultSet.TYPE_FORWARD_ONLY));
			logger.info("ResultSet.TYPE_SCROLL_INSENSITIVE\t:"
					+ conn.getMetaData().supportsResultSetType(
							ResultSet.TYPE_SCROLL_INSENSITIVE));
			logger.info("ResultSet.TYPE_SCROLL_SENSITIVE\t\t:"
					+ conn.getMetaData().supportsResultSetType(
							ResultSet.TYPE_SCROLL_SENSITIVE));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

	/**
	 * n条预定义SQL插入
	 * 
	 * @throws Exception
	 *             异常时抛出
	 */
	public void initData(int n,Connection conn) throws Exception {
		// init(); //初始化环境
		Long start = System.currentTimeMillis();
		String sql = "" + "insert into tuser\n"
				+ "    (name, remark, createtime, updatetime)\n" + "values\n"
				+ "    (?, ?, ?, ?)";
		//Connection conn = GetConn();
		conn.setAutoCommit(false);
		PreparedStatement pstmt = conn.prepareStatement(sql);
		try {			
			for (int i = 0; i < n; i++) {
				pstmt.setString(1, "NAME" + i);
				pstmt.setString(2, "REMARK" + i);
				pstmt.setDate(3, new Date(System.currentTimeMillis()));
				pstmt.setDate(4, new Date(System.currentTimeMillis()));
				pstmt.executeUpdate();
				
				//pstmt.close();
			}
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

		Long end = System.currentTimeMillis();
		logger.info("单条执行" + n + "条Insert操作，共耗时：" + (end - start) / 1000f
				+ "秒！");
		
	}

	/**
	 * 查询一条数据，并更新该条数据
	 * 
	 * @throws SQLException
	 */
	public void QueryOne4Update(Connection conn) throws SQLException {
		String query_sql = "select id, name, remark, createtime, updatetime\n"
				+ "    from tuser where id = 1";
		
		conn.setAutoCommit(false);
		// 注意结果集的参数配置
		Statement stmt = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY,
				ResultSet.CONCUR_UPDATABLE, ResultSet.CLOSE_CURSORS_AT_COMMIT);
		logger.info("连接是否为只读模式：" + conn.isReadOnly());
		logger.info("查询使用的SQL所对应的本地SQL脚本：" + conn.nativeSQL(query_sql));
		ResultSet rs = stmt.executeQuery(query_sql);
		try {
			while (rs.next()) { // 一行数据，本while可以省略
				// 更新数据的name列
				rs.updateString("name", "new name");
				// 保存更新行
				rs.updateRow();
			}
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

	/**
	 * 查询多条记录并做更新操作
	 * 
	 * @throws SQLException
	 */
	public void QueryMany4Update(Connection conn) throws SQLException {
		String query_sql = "select id, name, remark, createtime, updatetime\n"
				+ "    from tuser where id >20 and id<50";
		
		conn.setAutoCommit(false);
		Statement stmt = conn.createStatement(
				ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE,
				ResultSet.CLOSE_CURSORS_AT_COMMIT);
		ResultSet rs = stmt.executeQuery(query_sql);
		// 循环逐条更新查询结果集数据
		while (rs.next()) {
			// 更新数据的name列
			rs.updateString("name", "lavasoft"+rs.getInt("id"));
			rs.updateDate("updatetime", new Date(System.currentTimeMillis()));
			// 保存更新行
			rs.updateRow();
		}
		logger.info(conn.isReadOnly());
		logger.info(conn.nativeSQL(query_sql));
		conn.commit();

	}

	/**
	 * 查询一条记录并做插入操作
	 * 
	 * @throws SQLException
	 */
	public void QueryOne4Insert(Connection conn) throws SQLException {
		String query_sql = "select id, name, remark, createtime, updatetime\n"
				+ "    from tuser where id = 1 ";
		conn.setAutoCommit(false);
		Statement stmt = conn.createStatement(
				ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE,
				ResultSet.CLOSE_CURSORS_AT_COMMIT);
		ResultSet rs = stmt.executeQuery(query_sql);
		// 将结果集指针移动到可插入的行（这行是在内存中的一个虚拟行）
		rs.moveToInsertRow();
		// 设定行各个字段的数据
		rs.updateString(2, "熔岩");
		rs.updateString(3, "ttt");
		rs.updateDate(4, new Date(System.currentTimeMillis()));
		rs.updateDate(5, new Date(System.currentTimeMillis()));
		// 插入行数据到该表中
		rs.insertRow();
		// 指针复位：将指针移动到执行moveToInsertRow()之前的位置
		rs.moveToCurrentRow();
		conn.commit();

	}

	/**
	 * 查询一批数据，并做插入操作
	 * 
	 * @throws SQLException
	 */
	public void QueryMany4Insert(Connection conn) throws SQLException {
		String query_sql = "select id, name, remark, createtime, updatetime\n"
				+ "    from tuser where id >4 and id<8";

		conn.setAutoCommit(false);
		Statement stmt = conn.createStatement(
				ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE,
				ResultSet.CLOSE_CURSORS_AT_COMMIT);
		ResultSet rs = stmt.executeQuery(query_sql);
		while (rs.next()) {
			// 将结果集指针移动到可插入的行（这行是在内存中的一个虚拟行）
			rs.moveToInsertRow();
			// 设定行各个字段的数据
			rs.updateString(2, "lavasoft3");
			rs.updateString(3, "ttt");
			rs.updateDate(4, new Date(System.currentTimeMillis()));
			rs.updateDate(5, new Date(System.currentTimeMillis()));
			// 插入行数据到该表中
			rs.insertRow();
			// 指针复位：将指针移动到执行moveToInsertRow()之前的位置
			rs.moveToCurrentRow();
			// 将指针从当前位置下移一行
			rs.next();
		}
		conn.commit();

	}

	/**
	 * 查询一条数据，并做插入操作
	 * 
	 * @throws SQLException
	 */
	public void QueryOne4Delete(Connection conn) throws SQLException {
		String query_sql = "select id, name, remark, createtime, updatetime\n"
				+ "    from tuser where id=8";

		conn.setAutoCommit(false);
		Statement stmt = conn.createStatement(
				ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE,
				ResultSet.CLOSE_CURSORS_AT_COMMIT);
		ResultSet rs = stmt.executeQuery(query_sql);
		// 将指针移动到要删除的行上
		rs.next();
		// 从此 ResultSet 对象和底层数据库中删除当前行。指针不位于插入行上时不能调用此方法。
		rs.deleteRow();
		rs.next();
		conn.commit();

	}

	/**
	 * 查询一批数据，并做插入操作
	 * 
	 * @throws SQLException
	 */
	public void QueryMany4Delete(Connection conn) throws SQLException {
		String query_sql = "select id, name, remark, createtime, updatetime\n"
				+ "    from tuser where id>1";

		conn.setAutoCommit(false);
		Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
				ResultSet.CONCUR_UPDATABLE, ResultSet.CLOSE_CURSORS_AT_COMMIT);
		ResultSet rs = stmt.executeQuery(query_sql);
		while (rs.next()) {
			// 从此 ResultSet 对象和底层数据库中删除当前行。指针不位于插入行上时不能调用此方法。
			logger.info(rs.getRow());
			rs.deleteRow();
			rs.beforeFirst();
		}
		conn.commit();

	}

	// public static void main(String[] args) throws SQLException {
	// init();
	// // testQueryMany4Delete();
	// }
	public void closeconn(Connection conn) {

		
		try {
			if (conn != null)
				conn.close();
		} catch (SQLException se) {
			se.printStackTrace();
		}

	}
	public void closestmt(Statement stmt){
		try {
			if (stmt != null)
				stmt.close();
		} catch (SQLException se) {
			se.printStackTrace();
		}
	}
	public Connection GetOracleConn(){
		Connection conn=null;
		 String	 url= "jdbc:oracle:thin:@192.168.1.20:1521:orcl";
		 String	 username= "test";
		 String	  passwd="111111";

		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(ClassNotFoundException e){
			logger.info("获取驱动失败");
			e.printStackTrace();
		}
		try {
			conn=DriverManager.getConnection(url,username,passwd);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.info("Can not get Connection ,ERROR");
			e.printStackTrace();
		}
		return conn;
	}
	public  void java_sql_time() {
	       //获取日期和时间格式
	       //为了和下面 SQL 的日期做对比所以直接写明是 java.util.Date类
	       //我们也可以引入 java.util.Date包，然后声明为 Date 类
	      java.util.Date javaDate = new java.util.Date();
	      long javaTime = javaDate.getTime();
	      logger.info("The Java Date is:" + 
	             javaDate.toString());

	      //获取 SQL 的日期
	      java.sql.Date sqlDate = new java.sql.Date(javaTime);
	      logger.info("The SQL DATE is: " + 
	             sqlDate.toString());

	      //获取 SQL 的时间
	      java.sql.Time sqlTime = new java.sql.Time(javaTime);
	      logger.info("The SQL TIME is: " + 
	             sqlTime.toString());
	      //获取 SQL 的时间戳
	      java.sql.Timestamp sqlTimestamp =
	      new java.sql.Timestamp(javaTime);
	      logger.info("The SQL TIMESTAMP is: " + 
	             sqlTimestamp.toString());
	     }
	//static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	   //数据库的名称为 EXAMPLE
	   //static final String DB_URL = "jdbc:mysql://localhost/EXAMPLE";

	   //  数据库用户和密码
	   //static final String USER = "root";
	   //因为mumu 是在自己的电脑上做测试的，所以是有密码的
	   //static final String PASS = "0909";  

	   public  void autocommit(Connection conn) {
	       //Connection conn = null;
	       Statement stmt = null;
	       try{
	           //注册JDBC 驱动程序
	           Class.forName("com.mysql.jdbc.Driver");

	           //打开连接
	           logger.info("Connecting to database...");
	           //conn = DriverManager.getConnection(DB_URL,USER,PASS);
	           conn.setAutoCommit(false);  

	           //执行查询
	           logger.info("Creating statement...");
	           stmt = conn.createStatement();
	           //插入
	           String sql = "INSERT INTO t_user  " +
	                    "VALUES (9, 'Rose',20, 'Java')";
	           stmt.executeUpdate(sql);
	           sql="DELETE FROM t_user where id>6";
             stmt.executeUpdate(sql);
	           //查找
	           sql = "SELECT id, name, age FROM t_user";
	           ResultSet rs = stmt.executeQuery(sql);

	           //提交事务
	           conn.commit();
               logger.info("INSERT  SELECT  DELETE  SUCCESS");
	           //得到和处理结果集
	           while(rs.next()){
	               //检索
	               int id  = rs.getInt("id");
	               int age = rs.getInt("age");
	               String name = rs.getString("name");

	               //显示
	               logger.info("ID: " + id+", Age: " + age+", Name: " + name);
	               logger.info("---------------");
	           }
	           //清理环境
	           rs.close();
	           stmt.close();
	           conn.close();
	       }catch(SQLException se){
	           // JDBC 操作错误
	           se.printStackTrace();
	           // conn.rollback();
	           try{
	                 if(conn!=null)
	                    conn.rollback();
	              }catch(SQLException se2){
	                 se2.printStackTrace();
	              }
	       }catch(Exception e){
	           // Class.forName 错误
	           e.printStackTrace();
	       }finally{
	           //这里一般用来关闭资源的
	           try{
	               if(stmt!=null)
	                   stmt.close();
	           }catch(SQLException se2){
	           }
	           try{
	               if(conn!=null)
	                   conn.close();
	           }catch(SQLException se){
	               se.printStackTrace();
	           }
	       }
	       logger.info("Goodbye!");
	   }
	}
	

