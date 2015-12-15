package cn.wiztek.action;

import cn.wiztek.pojo.User;
import cn.wiztek.service.UserService;

import com.opensymphony.xwork2.ActionSupport;

public class GetUserAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private User user;
	private UserService userService;

	@Override
	public String execute() throws Exception {
		this.user = this.userService.findUserById(user.getId());
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
}
