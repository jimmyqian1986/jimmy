package cn.wiztek.dao;

import java.util.ArrayList;
import java.util.List;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import cn.wiztek.pojo.User;

public class UserDao extends HibernateDaoSupport implements IUserDao{

	@Override
	public String save(User user) {
		this.getHibernateTemplate().save(user);
		return "";
	}

	@Override
	public List<User> find() {
		List<User> list=new ArrayList<User>();
		String sql="from User u order by u.id asc";
		list=this.getHibernateTemplate().find(sql);
		return list;
	}

	@Override
	public String update(User user) {
		this.getHibernateTemplate().update(user);
		return "";
	}

	@Override
	public User findUser(Long id) {
		User user=new User();
		user=(User) this.getHibernateTemplate().get(User.class, id);
		return user;
	}

	@Override
	public String delete(User user) {
		this.getHibernateTemplate().delete(user);
		return "";
	}

	
	
	/*private SessionFactory sessionFactory;
	private ResultSet rs; 
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public List<User> find() {
		List<User> list=new ArrayList<User>();
		String sql="select * from song_student";
		Session session =sessionFactory.openSession();
		Transaction tc=session.beginTransaction();
		try {
			Statement st=session.connection().createStatement();
			rs=st.executeQuery(sql);
			while(rs.next()){
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setTel(rs.getString("tel"));
				user.setAddr(rs.getString("addr"));
				list.add(user);
				System.out.println("username======="+user.getUsername());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		tc.commit();
		session.close();
		return list;
	}*/
	
}
