/**
 * 
 */
package jimmy.commons;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.DriverManagerConnectionFactory;
import org.apache.commons.dbcp.PoolableConnection;
import org.apache.commons.dbcp.PoolableConnectionFactory;
import org.apache.commons.dbcp.PoolingDataSource;
import org.apache.commons.pool.impl.GenericObjectPool;

public class ConnectionFactory {

	private static interface Singleton {

	}

	private final DataSource dataSource;

	private ConnectionFactory(String username, String passwd, String url) {
		Properties properties = new Properties();
		properties.setProperty("user", username);
		properties.setProperty("password", passwd); // or get properties from
													// some configuration file

		GenericObjectPool<PoolableConnection> pool = new GenericObjectPool<PoolableConnection>();
		DriverManagerConnectionFactory connectionFactory = new DriverManagerConnectionFactory(
				url, properties);
		new PoolableConnectionFactory(connectionFactory, pool, null,
				"SELECT 1", 3, false, false,
				Connection.TRANSACTION_READ_COMMITTED);

		this.dataSource = new PoolingDataSource(pool);
	}

	public static Connection getMysqlConnection() throws SQLException {
		// try {
		// // ����MySql��������
		// Class.forName("com.mysql.jdbc.Driver");
		// System.out.println("START GET DRIVER");
		// } catch (ClassNotFoundException e) {
		// System.out.println("�Ҳ������������� ����������ʧ�ܣ�");
		// e.printStackTrace();
		// }
		String username = "test";
		String passwd = "111111";
		String url = "jdbc:mysql://192.168.1.20:3306/test";
		Connection con;
		// DriverManager.getConnection(url, username, passwd);
		final ConnectionFactory INSTANCE = new ConnectionFactory(username,
				passwd, url);
		con = INSTANCE.dataSource.getConnection();
		return con;
	}

	public static Connection getOracleConnection() throws SQLException {
		String username = "test";
		String passwd = "111111";
		String url = "jdbc:oracle:thin:@192.168.1.20:1521:orcl";
		final ConnectionFactory INSTANCE = new ConnectionFactory(username,
				passwd, url);
		INSTANCE.dataSource.getConnection().setAutoCommit(true);
		return INSTANCE.dataSource.getConnection();
	}
}
