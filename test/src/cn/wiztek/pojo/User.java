package cn.wiztek.pojo;

import java.io.Serializable;

public class User implements Serializable{
	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;
	private String username;
	private String password;
	private String tel;
	private String addr;
	
	/*private CustomerType state;
	
	public enum CustomerType {
        COMPETITOR, INVESTOR, PARTNER, VENDER
	}
	@Enumerated(javax.persistence.EnumType.STRING)
	public CustomerType getState() {
		return state;
	}
	public void setState(CustomerType state) {
		this.state = state;
	}*/
	
/*	public enum EnumType {
		ORDINAL,
		STRING
	}*/
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
}
