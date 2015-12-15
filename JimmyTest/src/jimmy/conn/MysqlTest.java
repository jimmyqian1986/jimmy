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
	// JDBC �����������ƺ����ݿ��ַ
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

			// ִ�в�ѯ
			logger.info("Creating statement...");
			// ��������Ҫ����һ��ͬѧ�����䣬��������
			String sql = "UPDATE t_user set age=? WHERE id=?";
			stmt = conn.prepareStatement(sql);

			// ��ֵ�󶨵����������������������Ϊ1��2...
			stmt.setInt(1, 200); // �� age ��ֵ(���Ϊ1)
			stmt.setInt(2, 1); // �� ID ��ֵ

			// ���� ID Ϊ1��ͬѧ������
			int rows = stmt.executeUpdate();
			logger.info("��Ӱ������� : " + rows);
			stmt.setInt(1, 300);
			stmt.setInt(2, 2);
			int rows2 = stmt.executeUpdate();
			logger.info("��Ӱ�������2 : " + rows2);
			// ��ѯ���м�¼������ʾ.
			sql = "SELECT id, name, age FROM t_user";
			ResultSet rs = stmt.executeQuery(sql);

			// ��������
			while (rs.next()) {
				// ����
				int id = rs.getInt("id");
				int age = rs.getInt("age");
				String name = rs.getString("name");

				// ��ʾ
				logger.info("ID: " + id + ", Age: " + age + ", Name: " + name);
				logger.info("--------------------");
			}
			// ����
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

			// ������Ƶ����һ��
			logger.info("Moving cursor to the last...");
			rs.last();

			// ��������
			logger.info("Displaying record...");
			int id = rs.getInt(1);
			int age = rs.getInt("age");
			String name = rs.getString("name");

			// ��ʾ
			logger.info("ID: " + id + ", Age: " + age + ", Name: " + name);
			logger.info("-------------------");

			// ������Ƶ���һ��
			logger.info("Moving cursor to the first row...");
			rs.first();

			logger.info("Displaying record...");
			id = rs.getInt(1);
			age = rs.getInt("age");
			name = rs.getString("name");

			// ��ʾ
			logger.info("ID: " + id + ", Age: " + age + ", Name: " + name);

			// �����������һ��
			logger.info("Moving cursor to the next row...");
			while (rs.next()) {

				logger.info("Displaying record...");
				id = rs.getInt(1);
				age = rs.getInt("age");
				name = rs.getString("name");

				// ��ʾ
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
		// ע��JDBC ��������
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// ������
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

			// ������в�������
			rs.moveToInsertRow();
			rs.updateInt("id", 500);
			rs.updateString("name", "John");
			rs.updateInt("age", 21);
			// �������ݿ�
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

			// ������в�������
			// rs.updateRow();
			// rs.updateInt("id",500);
			rs.updateString("name", "jackchen");
			// rs.updateInt("age",210);
			// �������ݿ�
			rs.updateRow();
			logger.info("UPDATE FROM RESULTSET ");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * JDBC����Update����Ż�
	 * 
	 * @author leizhimin 2009-7-30 8:38:33
	 */

	/**
	 * ��ʼ�����Ի���
	 * 
	 * @throws SQLException
	 *             �쳣ʱ�׳�
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
			logger.info("--------���ݿ���֧�ֵ�ResultSet������---------");
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
			logger.info("--------���ݿ���֧�ֵ�ResultSet������---------");
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
	 * n��Ԥ����SQL����
	 * 
	 * @throws Exception
	 *             �쳣ʱ�׳�
	 */
	public void initData(int n,Connection conn) throws Exception {
		// init(); //��ʼ������
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
		logger.info("����ִ��" + n + "��Insert����������ʱ��" + (end - start) / 1000f
				+ "�룡");
		
	}

	/**
	 * ��ѯһ�����ݣ������¸�������
	 * 
	 * @throws SQLException
	 */
	public void QueryOne4Update(Connection conn) throws SQLException {
		String query_sql = "select id, name, remark, createtime, updatetime\n"
				+ "    from tuser where id = 1";
		
		conn.setAutoCommit(false);
		// ע�������Ĳ�������
		Statement stmt = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY,
				ResultSet.CONCUR_UPDATABLE, ResultSet.CLOSE_CURSORS_AT_COMMIT);
		logger.info("�����Ƿ�Ϊֻ��ģʽ��" + conn.isReadOnly());
		logger.info("��ѯʹ�õ�SQL����Ӧ�ı���SQL�ű���" + conn.nativeSQL(query_sql));
		ResultSet rs = stmt.executeQuery(query_sql);
		try {
			while (rs.next()) { // һ�����ݣ���while����ʡ��
				// �������ݵ�name��
				rs.updateString("name", "new name");
				// ���������
				rs.updateRow();
			}
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

	/**
	 * ��ѯ������¼�������²���
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
		// ѭ���������²�ѯ���������
		while (rs.next()) {
			// �������ݵ�name��
			rs.updateString("name", "lavasoft"+rs.getInt("id"));
			rs.updateDate("updatetime", new Date(System.currentTimeMillis()));
			// ���������
			rs.updateRow();
		}
		logger.info(conn.isReadOnly());
		logger.info(conn.nativeSQL(query_sql));
		conn.commit();

	}

	/**
	 * ��ѯһ����¼�����������
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
		// �������ָ���ƶ����ɲ�����У����������ڴ��е�һ�������У�
		rs.moveToInsertRow();
		// �趨�и����ֶε�����
		rs.updateString(2, "����");
		rs.updateString(3, "ttt");
		rs.updateDate(4, new Date(System.currentTimeMillis()));
		rs.updateDate(5, new Date(System.currentTimeMillis()));
		// ���������ݵ��ñ���
		rs.insertRow();
		// ָ�븴λ����ָ���ƶ���ִ��moveToInsertRow()֮ǰ��λ��
		rs.moveToCurrentRow();
		conn.commit();

	}

	/**
	 * ��ѯһ�����ݣ������������
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
			// �������ָ���ƶ����ɲ�����У����������ڴ��е�һ�������У�
			rs.moveToInsertRow();
			// �趨�и����ֶε�����
			rs.updateString(2, "lavasoft3");
			rs.updateString(3, "ttt");
			rs.updateDate(4, new Date(System.currentTimeMillis()));
			rs.updateDate(5, new Date(System.currentTimeMillis()));
			// ���������ݵ��ñ���
			rs.insertRow();
			// ָ�븴λ����ָ���ƶ���ִ��moveToInsertRow()֮ǰ��λ��
			rs.moveToCurrentRow();
			// ��ָ��ӵ�ǰλ������һ��
			rs.next();
		}
		conn.commit();

	}

	/**
	 * ��ѯһ�����ݣ������������
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
		// ��ָ���ƶ���Ҫɾ��������
		rs.next();
		// �Ӵ� ResultSet ����͵ײ����ݿ���ɾ����ǰ�С�ָ�벻λ�ڲ�������ʱ���ܵ��ô˷�����
		rs.deleteRow();
		rs.next();
		conn.commit();

	}

	/**
	 * ��ѯһ�����ݣ������������
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
			// �Ӵ� ResultSet ����͵ײ����ݿ���ɾ����ǰ�С�ָ�벻λ�ڲ�������ʱ���ܵ��ô˷�����
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
			logger.info("��ȡ����ʧ��");
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
	       //��ȡ���ں�ʱ���ʽ
	       //Ϊ�˺����� SQL ���������Ա�����ֱ��д���� java.util.Date��
	       //����Ҳ�������� java.util.Date����Ȼ������Ϊ Date ��
	      java.util.Date javaDate = new java.util.Date();
	      long javaTime = javaDate.getTime();
	      logger.info("The Java Date is:" + 
	             javaDate.toString());

	      //��ȡ SQL ������
	      java.sql.Date sqlDate = new java.sql.Date(javaTime);
	      logger.info("The SQL DATE is: " + 
	             sqlDate.toString());

	      //��ȡ SQL ��ʱ��
	      java.sql.Time sqlTime = new java.sql.Time(javaTime);
	      logger.info("The SQL TIME is: " + 
	             sqlTime.toString());
	      //��ȡ SQL ��ʱ���
	      java.sql.Timestamp sqlTimestamp =
	      new java.sql.Timestamp(javaTime);
	      logger.info("The SQL TIMESTAMP is: " + 
	             sqlTimestamp.toString());
	     }
	//static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	   //���ݿ������Ϊ EXAMPLE
	   //static final String DB_URL = "jdbc:mysql://localhost/EXAMPLE";

	   //  ���ݿ��û�������
	   //static final String USER = "root";
	   //��Ϊmumu �����Լ��ĵ����������Եģ��������������
	   //static final String PASS = "0909";  

	   public  void autocommit(Connection conn) {
	       //Connection conn = null;
	       Statement stmt = null;
	       try{
	           //ע��JDBC ��������
	           Class.forName("com.mysql.jdbc.Driver");

	           //������
	           logger.info("Connecting to database...");
	           //conn = DriverManager.getConnection(DB_URL,USER,PASS);
	           conn.setAutoCommit(false);  

	           //ִ�в�ѯ
	           logger.info("Creating statement...");
	           stmt = conn.createStatement();
	           //����
	           String sql = "INSERT INTO t_user  " +
	                    "VALUES (9, 'Rose',20, 'Java')";
	           stmt.executeUpdate(sql);
	           sql="DELETE FROM t_user where id>6";
             stmt.executeUpdate(sql);
	           //����
	           sql = "SELECT id, name, age FROM t_user";
	           ResultSet rs = stmt.executeQuery(sql);

	           //�ύ����
	           conn.commit();
               logger.info("INSERT  SELECT  DELETE  SUCCESS");
	           //�õ��ʹ�������
	           while(rs.next()){
	               //����
	               int id  = rs.getInt("id");
	               int age = rs.getInt("age");
	               String name = rs.getString("name");

	               //��ʾ
	               logger.info("ID: " + id+", Age: " + age+", Name: " + name);
	               logger.info("---------------");
	           }
	           //������
	           rs.close();
	           stmt.close();
	           conn.close();
	       }catch(SQLException se){
	           // JDBC ��������
	           se.printStackTrace();
	           // conn.rollback();
	           try{
	                 if(conn!=null)
	                    conn.rollback();
	              }catch(SQLException se2){
	                 se2.printStackTrace();
	              }
	       }catch(Exception e){
	           // Class.forName ����
	           e.printStackTrace();
	       }finally{
	           //����һ�������ر���Դ��
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
	

