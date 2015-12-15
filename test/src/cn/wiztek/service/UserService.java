package cn.wiztek.service;

import java.util.List;

import cn.wiztek.dao.IUserDao;
import cn.wiztek.pojo.User;

public class UserService implements IUserService{

	private IUserDao userDao;
	
	@Override
	public List<User> find() {
		return userDao.find();
	}

	public IUserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

	public String save(User user) {
		return userDao.save(user);
	}

	public String update(User user) {
		return userDao.update(user);
	}

	public User findUserById(Long id) {
		return userDao.findUser(id);
	}

	public String deleteUser(User user) {
		return userDao.delete(user);
	}
}
