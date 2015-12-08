package com.jimmy.Action;

import java.io.UnsupportedEncodingException;

import org.apache.log4j.Logger;

import com.jimmy.Util.DESUtil;
import com.opensymphony.xwork2.ActionSupport;

public class loginAction extends ActionSupport {
	private String username;
	private String password;
	DESUtil desutil = new DESUtil();
	private final static Logger logger = Logger.getLogger(loginAction.class);

	public static void main(String[] args) {
		loginAction login = new loginAction();

		logger.info(" this is message ");
	}

	public String execute() {
		String result = ERROR;
		try {
			if (desutil.encrypt(username).equals(desutil.encrypt(password))) {
				logger.debug("HTTP SUCCESS DEBUG");
				logger.info("HTTP SUCCESS INFO");
				result = SUCCESS;
			} else {
				logger.error("HTTP ERROR");
				logger.fatal("HTTP ERROR FATAL");
				result = ERROR;
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
}
