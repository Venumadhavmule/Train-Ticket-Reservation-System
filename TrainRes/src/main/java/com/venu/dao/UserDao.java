package com.venu.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.venu.model.UserBean;
import com.venu.util.UhbCon;

public class UserDao {
	// saveUser
	// getAllUsers
	// getUserByUserName
	// UpdateUser
	// DeleteUser

	public void saveUser(UserBean user) {
		Transaction transaction = null;
		try (Session session = UhbCon.getConnection().openSession()) {
			transaction = session.beginTransaction();
			session.save(user);
			transaction.commit();

		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}

		}
	}

	public static List<UserBean> getAllUsers() {

		Transaction transaction = null;
		List<UserBean> allUsers = null;

		try (Session session = UhbCon.getConnection().openSession()) {
			transaction = session.beginTransaction();
			allUsers = session.createQuery("from user").list();
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		}
		return allUsers;
	}

	public UserBean getUserByUserName(String username) {
		Transaction transaction = null;
		UserBean user = null;
		try (Session session = UhbCon.getConnection().openSession()) {
			
			transaction = session.beginTransaction();
			user = (UserBean) session.createQuery("from user where username=:username");
			
			// user = session.get(UserBean.class, username);

			transaction.commit();
		} catch (Exception e) {
			if (transaction != null)
				transaction.rollback();

		}
		return user;
	}

	public void updateUser(UserBean user) {
		Transaction transaction = null;
		UserBean existUser = null;
		try (Session session = UhbCon.getConnection().openSession()) {
			transaction = session.beginTransaction();

//			existUser = (UserBean)session.createQuery("from user where username=:username");
//			existUser.setFirstName(user.getFirstName());
//			existUser.setLastName(user.getLastName());
//			existUser.setPhone(user.getPhone());
//			existUser.setEmail(user.getEmail());
//			existUser.setUsername(user.getUsername());
//			existUser.setPassword(user.getPassword());

			session.saveOrUpdate(user);

			transaction.commit();

		}
	}

	public void deleteUser(String username) {
		Transaction transaction = null;
		UserBean user = null;
		try (Session session = UhbCon.getConnection().openSession()) {
			transaction = session.beginTransaction();
			user = session.get(UserBean.class, username);
			session.delete(user);

			transaction.commit();

		}catch (Exception e) {
			if(transaction!=null) {
				transaction.rollback();
			}
		}

	}

}