package cn.wiztek.action;

import java.util.List;

import org.apache.catalina.connector.Request;

import cn.wiztek.pojo.User;
import cn.wiztek.service.UserService;

import com.opensymphony.xwork2.ActionSupport;

public class ListUserAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private User user;
	private static UserService userService;
	private static List<User> list;
	private Request request;

	public static List<User> find() {
		list = userService.find();
		// request.setAttribute("list", list);
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String execute() throws Exception {
		// find();
		// Map request = (Map) ActionContext.getContext().get("request");
		// request.put("list", list);
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

	public static void main(String[] args) {
		// find();
		// Map request = (Map) ActionContext.getContext().get("request");
		// request.put("list", list);
		List list = find();
		for (int i = 0; i < list.size(); i++) {
			System.out.println("  name" + list.get(0).toString());

		}
	}
}
