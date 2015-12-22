package com.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserBeanCl {
	private Statement sm = null;
	private Connection ct = null;
	private ResultSet rs = null;

	public void close() {
		try {

			if (sm != null) {
				sm.close();
				sm = null;
			}

			if (ct != null) {
				ct.close();
				ct = null;
			}

			if (rs != null) {
				rs.close();
				rs = null;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// ����¼�û��Ƿ�Ϸ�
	public boolean checkUser(String user, String password) {
		boolean b = false;
		try {

			// �������
			ct = new ConnDB().getConn();
			// ����statement
			sm = ct.createStatement();

			rs = sm.executeQuery("select * from user where username=" + user
					+ "");

			if (rs.next()) {
				// ˵���û�����
				String pwd = rs.getString(3);
				if (password.equals(pwd)) {
					// ˵��������ȷ
					b = true;
				} else {
					b = false;
				}

			} else {
				b = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.close();
		}

		return b;
	}

}