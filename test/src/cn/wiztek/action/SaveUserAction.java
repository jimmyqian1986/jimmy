package cn.wiztek.action;

import java.util.List;

import org.apache.catalina.connector.Request;

import cn.wiztek.pojo.User;
import cn.wiztek.service.UserService;

import com.opensymphony.xwork2.ActionSupport;

public class SaveUserAction extends ActionSupport{

	private static final long serialVersionUID = 1L;
	private User user;
	private UserService userService;
	private List<User> list;
	private Request request;
	
	@Override
	public String execute() throws Exception{
		userService.save(user);
		return SUCCESS;
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public List<User> getList() {
		return list;
	}
	public void setList(List<User> list) {
		this.list = list;
	}
	public Request getRequest() {
		return request;
	}
	public void setRequest(Request request) {
		this.request = request;
	}
}
