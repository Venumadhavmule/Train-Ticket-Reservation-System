package com.venu.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.venu.model.UserBean;

public class UhbCon {
	public static SessionFactory getConnection() {
		Configuration con = new Configuration().configure().addAnnotatedClass((UserBean.class));
		ServiceRegistry reg = new StandardServiceRegistryBuilder().applySettings(con.getProperties()).build();
		SessionFactory sf = con.buildSessionFactory(reg);
		return sf;
	}
}
