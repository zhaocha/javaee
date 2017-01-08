package com.copyfu.dao;


import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.copyfu.entity.User;

public class CheckLogin {
	private static String salt = "fu";  // 保存密码时加的盐，出于安全考虑
	//读取hibernate的配置文件  即 hibernate.cfg.xml
	private static Configuration cfg = new Configuration().configure(); 
    private static SessionFactory factory = cfg.buildSessionFactory();
    // 检查是否为合法用户
	public static int check(String username, String password, int authority) {  
        Session session = null;
        Transaction btr = null;
        List<User> result = null;
        try{
          //开启一个事务  
        	session = factory.openSession();
            btr = session.beginTransaction();
            result = (List<User>) session.createSQLQuery("SELECT * FROM `user` WHERE `username` = ? ").addEntity(User.class).setParameter(0, username).list();
            btr.commit();
            password = getMd5(password);
        }catch(Exception e){
        	e.printStackTrace();
        	return 1; //异常，未知错误
        }
        finally{
        	if(session != null){
        		session.close();
        	}
        }
        if(result.size() == 0){
        	return 2;//用户不存在
        }
        else if(result.get(0).getAuthority() != authority ){
        	return 2; //权限错误
        }
        else if(!result.get(0).getPassword().equals(password)){
        	return 3; //密码错误
        }
        return 0;
	}
	
	// 对password进行md5加密
	public static String getMd5(String password) throws NoSuchAlgorithmException{
		try{
			MessageDigest md5 = MessageDigest.getInstance("MD5");
	         password = password+salt;
	         md5.update(password.getBytes());
	         password = new BigInteger(1, md5.digest()).toString(64);
	         return password;
		}catch(Exception e){
			 e.printStackTrace();
		}
		return password; 
         
	}
}
