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
	private static String salt = "fu";  // ��������ʱ�ӵ��Σ����ڰ�ȫ����
	//��ȡhibernate�������ļ�  �� hibernate.cfg.xml
	private static Configuration cfg = new Configuration().configure(); 
    private static SessionFactory factory = cfg.buildSessionFactory();
    // ����Ƿ�Ϊ�Ϸ��û�
	public static int check(String username, String password, int authority) {  
        Session session = null;
        Transaction btr = null;
        List<User> result = null;
        try{
          //����һ������  
        	session = factory.openSession();
            btr = session.beginTransaction();
            result = (List<User>) session.createSQLQuery("SELECT * FROM `user` WHERE `username` = ? ").addEntity(User.class).setParameter(0, username).list();
            btr.commit();
            password = getMd5(password);
        }catch(Exception e){
        	e.printStackTrace();
        	return 1; //�쳣��δ֪����
        }
        finally{
        	if(session != null){
        		session.close();
        	}
        }
        if(result.size() == 0){
        	return 2;//�û�������
        }
        else if(result.get(0).getAuthority() != authority ){
        	return 2; //Ȩ�޴���
        }
        else if(!result.get(0).getPassword().equals(password)){
        	return 3; //�������
        }
        return 0;
	}
	
	// ��password����md5����
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
