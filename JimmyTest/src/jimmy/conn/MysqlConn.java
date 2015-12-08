/**
 * 
 */
package jimmy.conn;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author JH
 *
 */
public class MysqlConn {
	static String url = "jdbc:mysql://192.168.1.20:3306/test";
	static String username = "test";
	static String password = "111111";

	public static void main(String[] args) {
		System.out.println("Mysql Conn Test.");
		MysqlConn mycon = new MysqlConn();
		mycon.GetDriver();
		//Connection con=null;
		System.out.println("START ------------------------------------START");
		Connection con=mycon.GetConn(url, username, password);
		Statement stmt=mycon.GetStmt(con);
		for(int i=1;i<2000;i++){
			System.out.println("Now The I is :"+i);
			double j=0;
			j=Math.random()*9000+1000;
		String sql="insert t_user values("+i+",'"+(char)(int)Math.floor(j)+"')";
		boolean flag =mycon.InsertData(stmt, sql);
		if(flag!=false){
			System.out.println("INSERT DATA FAILE");
		}else {
			System.out.println("INSRT DATA SUCCESS");
		}
		}
		//mycon.InsertData(stmt, "truncate table t_user");
		ResultSet rset=mycon.GetResult(stmt);
		mycon.GetRs(rset);
		
		mycon.CloseAll(rset,stmt, con);
		System.out.println("END----------------------------------------END");
	}

	// 1、加载JDBC驱动程序：
	// 在连接数据库之前，首先要加载想要连接的数据库的驱动到JVM（Java虚拟机），
	// 这通过java.lang.Class类的静态方法forName(String className)实现。
	// 例如：
	public void GetDriver() {
		try {
			// 加载MySql的驱动类
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("START GET DRIVER");
		} catch (ClassNotFoundException e) {
			System.out.println("找不到驱动程序类 ，加载驱动失败！");
			e.printStackTrace();
		}
	}

	// 2、提供JDBC连接的URL
	// •连接URL定义了连接数据库时的协议、子协议、数据源标识。
	// •书写形式：协议：子协议：数据源标识
	// 协议：在JDBC中总是以jdbc开始
	// 子协议：是桥连接的驱动程序或是数据库管理系统名称。
	// 数据源标识：标记找到数据库来源的地址与连接端口。
	// 例如：（MySql的连接URL）
	// jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=gbk ;
	// useUnicode=true：表示使用Unicode字符集。如果characterEncoding设置为
	// gb2312或GBK，本参数必须设置为true 。characterEncoding=gbk：字符编码方式。
	// 3、创建数据库的连接
	// •要连接数据库，需要向java.sql.DriverManager请求并获得Connection对象，
	// 该对象就代表一个数据库的连接。
	// •使用DriverManager的getConnectin(String url , String username ,
	// String password )方法传入指定的欲连接的数据库的路径、数据库的用户名和
	// 密码来获得。
	// 例如：
	// 连接MySql数据库，用户名和密码都是root
	public Connection GetConn(String url, String username, String password) {
		try {
			Connection con = DriverManager.getConnection(url, username,
					password);
			System.out.println("START GET CONN");
			return con;
		} catch (SQLException se) {
			System.out.println("数据库连接失败！");
			se.printStackTrace();
		}
		return null;

	}

	// 4、创建一个Statement
	// •要执行SQL语句，必须获得java.sql.Statement实例，Statement实例分为以下3
	// 种类型：
	// 1、执行静态SQL语句。通常通过Statement实例实现。
	// 2、执行动态SQL语句。通常通过PreparedStatement实例实现。
	// 3、执行数据库存储过程。通常通过CallableStatement实例实现。
	// 具体的实现方式：
	public Statement GetStmt(Connection conn) {
		try {
			Statement stmt = conn.createStatement();
			System.out.println("START GET STMT");
			return stmt;
			// PreparedStatement pstmt = conn.prepareStatement(sql) ;
			// CallableStatement cstmt =
			// conn.prepareCall("{CALL demoSp(? , ?)}") ;
		} catch (SQLException se) {
			System.out.println("statement获取失败！");
			se.printStackTrace();
		}
		return null;

	}

	public PreparedStatement GetPsStmt(Connection conn, String sql) {
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			System.out.println("START GET GetPsStmt");
			return pstmt;
			// CallableStatement cstmt =
			// conn.prepareCall("{CALL demoSp(? , ?)}") ;
		} catch (SQLException se) {
			System.out.println("PreparedStatement获取失败！");
			se.printStackTrace();
		}
		return null;

	}

	public CallableStatement GetCaStmt(Connection conn, String sql) {
		try {
			CallableStatement cstmt = conn.prepareCall(sql);
			System.out.println("START GET GetPsStmt");
			return cstmt;
		} catch (SQLException se) {
			System.out.println("CallableStatement获取失败！");
			se.printStackTrace();
		}
		return null;

	}

	// 5、执行SQL语句
	// Statement接口提供了三种执行SQL语句的方法：executeQuery 、executeUpdate
	// 和execute
	// 1、ResultSet executeQuery(String sqlString)：执行查询数据库的SQL语句
	// ，返回一个结果集（ResultSet）对象。
	// 2、int executeUpdate(String sqlString)：用于执行INSERT、UPDATE或
	// DELETE语句以及SQL DDL语句，如：CREATE TABLE和DROP TABLE等
	// 3、execute(sqlString):用于执行返回多个结果集、多个更新计数或二者组合的
	// 语句。
	// 具体实现的代码：
	public ResultSet GetResult(Statement stmt){
		
		try {
			ResultSet rs = stmt.executeQuery("SELECT * FROM t_user");
			System.out.println("START GET GetPsStmt");
			return rs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("GetResult获取失败！");
			e.printStackTrace();
		}
		return null;
		
		// int rows = stmt.executeUpdate("INSERT INTO ...") ;
		// boolean flag = stmt.execute(String sql) {}
	}
	public boolean InsertData(Statement stmt,String sql){
		 boolean flag=true;
		try {
			 flag= stmt.execute(sql);
			return flag;
		} catch (SQLException e){
			System.out.println("INSERT DATA ERROR");
			e.printStackTrace();
		}
		return flag;
	}
	//6、处理结果   
//    两种情况：   
//     1、执行更新返回的是本次操作影响到的记录数。   
//     2、执行查询返回的结果是一个ResultSet对象。   
//    • ResultSet包含符合SQL语句中条件的所有行，并且它通过一套get方法提供了对这些   
//      行中数据的访问。   
//    • 使用结果集（ResultSet）对象的访问方法获取数据：
	public void GetRs(ResultSet rs){
    
         try { while(rs.next()){
			String name = rs.getString("name") ;
			String pass = rs.getString(1) ;
			System.out.println("this is :"+name);
			System.out.println("this is query by 1 :"+pass);
         }} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
    // 此方法比较高效   
     }   
	
//    （列是从左到右编号的，并且从列1开始）   
//	 7、关闭JDBC对象    
//    操作完成以后要把所有使用的JDBC对象全都关闭，以释放JDBC资源，关闭顺序和声   
//    明顺序相反：   
//    1、关闭记录集   
//    2、关闭声明   
//    3、关闭连接对象  
	public void CloseAll(ResultSet rs,Statement stmt,Connection conn){
         if(rs != null){   // 关闭记录集   
       try{   
           rs.close() ; 
           System.out.println("CLOSE RESULTSET");
       }catch(SQLException e){   
           e.printStackTrace() ;   
       }   
         }   
         if(stmt != null){   // 关闭声明   
       try{   
           stmt.close() ;   
           System.out.println("CLOSE STMT");
       }catch(SQLException e){   
           e.printStackTrace() ;   
       }   
         }   
         if(conn != null){  // 关闭连接对象   
        try{   
           conn.close() ;   
           System.out.println("CLOSE CONNECTION");
        }catch(SQLException e){   
           e.printStackTrace() ;   
        }   
         } 
	}
}
