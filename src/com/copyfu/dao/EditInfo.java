package com.copyfu.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.copyfu.entity.Student;
import com.copyfu.entity.User;

public class EditInfo {
	private static Configuration cfg = new Configuration().configure();
	private static SessionFactory factory = cfg.buildSessionFactory();
	private static Session session = null;  
    private static Transaction btr = null;
    //在数据库中添加学生信息
	public static boolean add(int stu_id, String stu_name, String sex, int age, String stu_class, String phone, String address){
		try{	
        	session = factory.openSession();
            btr = session.beginTransaction();
            session.createSQLQuery("INSERT INTO `student`(`stu_id`,`stu_name`,`sex`,`age`,`stu_class`,`phone`,`address`) VALUES(?,?,?,?,?,?,?)").setParameter(0, stu_id).setParameter(1, stu_name).setParameter(2, sex).setParameter(3, age).setParameter(4, stu_class).setParameter(5, phone).setParameter(6, address).executeUpdate();
            btr.commit();
        }catch(Exception e){
        	e.printStackTrace();
        	btr.rollback();
        	return false;
        }finally{
        	if(session != null ){
        		session.close();
        	}
        }
		return true;
	}
	
	//在数据库中删除学生信息
	public static boolean delete(int stu_id){
		try{	  
        	session = factory.openSession();
            btr = session.beginTransaction();
	        session.createSQLQuery("DELETE FROM `student` WHERE `stu_id` = ? ").setParameter(0, stu_id).executeUpdate();
            btr.commit();
        }catch(Exception e){
        	e.printStackTrace();
        	btr.rollback();
        	return false; //异常，未知错误
        }finally{
        	if(session != null ){
        		session.close();
        	}
        }
		return true;
	}
	
	//在数据库中更新学生信息
	public static boolean update(int stu_id, String stu_name, String sex, int age, String stu_class, String phone, String address){
		try{	  
        	session = factory.openSession();
            btr = session.beginTransaction();
	        session.createSQLQuery("UPDATE `student` SET  `stu_name`=?, `sex`=?, `age`=?, `stu_class`=?, `phone`=?, `address`=? WHERE `stu_id`=?").setParameter(0, stu_name).setParameter(1, sex).setParameter(2, age).setParameter(3, stu_class).setParameter(4, phone).setParameter(5, address).setParameter(6, stu_id).executeUpdate();
            btr.commit();
        }catch(Exception e){
        	e.printStackTrace();
        	btr.rollback();
        	return false; //异常，未知错误
        }finally{
        	if(session != null ){
        		session.close();
        	}
        }
		return true;
	}
	
	//从数据库中查找出所有学生信息
	public static List<Student> selectAll(){
		String hql = "from Student";
		List<Student> result = null;
		try{	  
        	session = factory.openSession();  	
            btr = session.beginTransaction();
            result = (List<Student>) session.createQuery(hql).list();
            btr.commit();
        }catch(Exception e){
        	e.printStackTrace();
        	return null; //异常，未知错误
        }finally{
        	if(session != null){
        		session.close();
        	}
        }
		return result;
	}
	
	// 从数据库中查询出单个学生信息
	public static List<Student> selectStu(int stu_id){
		List<Student> result = null;
		try{	  
        	session = factory.openSession();  	
            btr = session.beginTransaction();
            result = (List<Student>) session.createSQLQuery("SELECT * FROM `student` WHERE `stu_id` = ? ").addEntity(Student.class).setParameter(0, stu_id).list();
            btr.commit();
        }catch(Exception e){
        	e.printStackTrace();
        	return null; //异常，未知错误
        }finally{
        	if(session != null){
        		session.close();
        	}
        }
		return result;
	}
	
}
