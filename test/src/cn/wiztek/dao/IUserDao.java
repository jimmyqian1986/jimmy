package cn.wiztek.dao;

import java.util.List;

import cn.wiztek.pojo.User;

public interface IUserDao {

	public List<User> find();

	public String save(User user);

	public String update(User user);

	public User findUser(Long id);

	public String delete(User user);
	
}
