package com.copyfu.dao;

import java.lang.reflect.Array;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.copyfu.entity.Student;

// ͳ��ѧ����Ϣ
public class Statistics {
	private static Configuration cfg = new Configuration().configure();
	private static SessionFactory factory = cfg.buildSessionFactory();
	private static Session session = null;  
    private static Transaction btr = null;
    //�����ݿ��в�ѯ��ͳ�ư༶ѧ������
    public static List<Object[]> classPerson(){
    	String sql = "SELECT `stu_class`,COUNT(*) FROM `student` GROUP BY `stu_class`";
		List<Object[]> result = null;
		try{	  
        	session = factory.openSession();  	
            btr = session.beginTransaction();
            result = (List<Object[]>) session.createSQLQuery(sql).list();
            btr.commit();
        }catch(Exception e){
        	e.printStackTrace();
        	return null; //�쳣��δ֪����
        }finally{
        	if(session != null){
        		session.close();
        	}
        }
		return result;
    }
    
  //�����ݿ��в�ѯ��ͳ�ư༶ѧ���Ա�
    public static List<Object[]> studentSex(){
    	String sql = "SELECT `sex`,COUNT(*) FROM `student` GROUP BY `sex`";
		List<Object[]> result = null;
		try{	  
        	session = factory.openSession();  	
            btr = session.beginTransaction();
            result = (List<Object[]>) session.createSQLQuery(sql).list();
            btr.commit();     	
        }catch(Exception e){
        	e.printStackTrace();
        	return null; //�쳣��δ֪����
        }finally{
        	if(session != null){
        		session.close();
        	}
        }
		return result;
    }

  //�����ݿ��в�ѯ��ͳ�ư༶ѧ������
    public static List<Object[]> studentAge(){
    	String sql = "SELECT `age`,COUNT(*) FROM `student` GROUP BY `age`";
		List<Object[]> result = null;
		try{	  
        	session = factory.openSession();  	
            btr = session.beginTransaction();
            result = (List<Object[]>) session.createSQLQuery(sql).list();
            btr.commit();     	
        }catch(Exception e){
        	e.printStackTrace();
        	return null; //�쳣��δ֪����
        }finally{
        	if(session != null){
        		session.close();
        	}
        }
		return result;
    }
}