package com.copyfu.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.copyfu.entity.User;

// 处理普通用户注册
public class Register {
		private static Configuration cfg = new Configuration().configure();  
	    private static SessionFactory factory = cfg.buildSessionFactory();  
	    // 注册一个新的用户
	    public static int register(String username, String password, String repassword) {  
	    	boolean res = checkRegister(username);
	    	if(!res) return 2;
	    	else if(!password.equals(repassword)) {
	    		return 3;
	    	}
	    	Session session = null;
	        Transaction btr = null;
	        try{
	        	session = factory.openSession();
	            btr = session.beginTransaction();
	            // 对password进行md5加密
	            password = CheckLogin.getMd5(password);  
	            session.createSQLQuery("INSERT INTO `user`(`username`,`password`,`authority`) VALUES(?,?,1)").setParameter(0, username).setParameter(1, password).executeUpdate();
	            btr.commit();
	        }catch(Exception e){
	        	e.printStackTrace();
	        	return 1; //异常，未知错误
	        }
	        finally{
	        	if(session != null){
	        		session.close();
	        	}
	        }
	        return 0;
		}
	    
	    // 检查用户是否已经存在
		public static boolean checkRegister(String username) {  
	        Session session = null;
	        Transaction btr = null;
	        List<User> result = null;
	        try{
	        	session = factory.openSession();
	            btr = session.beginTransaction();
	            result = (List<User>) session.createSQLQuery("SELECT * FROM `user` WHERE `username` = ? ").addEntity(User.class).setParameter(0, username).list();
	            btr.commit();
	        }catch(Exception e){
	        	e.printStackTrace();
	        	return false; //异常，未知错误
	        }
	        finally{
	        	if(session != null){
	        		session.close();
	        	}
	        }
	        if(result.size() != 0){
	        	return false;
	        }
	        return true;
		}
}
