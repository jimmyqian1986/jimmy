package com.model;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnDB {
	private Connection ct = null;

	public Connection getConn() {

		try {
			// ��������
			Class.forName("com.mysql.jdbc.Driver");

			// �õ�����
			ct = DriverManager
					.getConnection("jdbc:mysql://localhost:3306/hadoop?user=root&password=");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ct;
	}
}