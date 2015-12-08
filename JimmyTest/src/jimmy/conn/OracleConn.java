/**
 * 
 */
package jimmy.conn;

/**
 * @author JH
 *
 */
import java.sql.*;

public class OracleConn {

static String	 url= "jdbc:oracle:thin:@192.168.56.101:1521:orcl";
static String	 username= "test";
static String	  passwd="111111";


public static void main(String[] args) {
  ResultSet rs = null;
  Statement stmt = null;
  Connection conn = null;

  try {
   Class.forName("oracle.jdbc.driver.OracleDriver");
   //new oracle.jdbc.driver.OracleDriver();
   conn = DriverManager.getConnection(url, username, passwd);
   stmt = conn.createStatement();
   rs = stmt.executeQuery("select * from dept");
   while(rs.next()) {
    System.out.println(rs.getString("deptno"));
    System.out.println(rs.getInt(1));
    System.out.println(rs.getString("dname"));
   }
  } catch (ClassNotFoundException e) {
   e.printStackTrace();
  } catch (SQLException e) {
   e.printStackTrace();
  } finally {
   try {
    if(rs != null) {
     rs.close();
     rs = null;
    }
    if(stmt != null) {
     stmt.close();
     stmt = null;
    }
    if(conn != null) {
     conn.close();
     conn = null;
    }
   } catch (SQLException e) {
    e.printStackTrace();
   }
  }
 }
public void GetDriver(){
	try{
		Class.forName("oracle.jdbc.driver.OracleDriver");
	}catch(ClassNotFoundException e){
		System.out.println("获取驱动失败");
		e.printStackTrace();
	}
}
public Connection GetConn(String url,String username,String passwd){
	Connection conn=null;
	try {
		conn=DriverManager.getConnection(url,username,passwd);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		System.out.println("Can not get Connection ,ERROR");
		e.printStackTrace();
	}
	return conn;
}
public Connection GetOracleConn(){
	Connection conn=null;
	try{
		Class.forName("oracle.jdbc.driver.OracleDriver");
	}catch(ClassNotFoundException e){
		System.out.println("获取驱动失败");
		e.printStackTrace();
	}
	try {
		conn=DriverManager.getConnection(url,username,passwd);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		System.out.println("Can not get Connection ,ERROR");
		e.printStackTrace();
	}
	return conn;
}
}



