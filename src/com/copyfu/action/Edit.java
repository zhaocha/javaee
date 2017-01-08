package com.copyfu.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.copyfu.dao.EditInfo;
import com.copyfu.entity.Student;
import com.opensymphony.xwork2.ActionContext;

// �����ѧ����Ϣ����ɾ���
public class Edit {
	private int stu_id;
	private String stu_name;
	private String sex;
	private int age;
	private String stu_class;
	private int phone;
	private String address;
	private ActionContext context = ActionContext.getContext();
	Map<String, Object> session = context.getSession();
	// ��ʼ��֪��struts2 �п���ֱ�ӻ�ȡǰ�˴�����ֵ����ΪҪ�õ�request�������ø���
	private HttpServletRequest request = (HttpServletRequest)context.get(ServletActionContext.HTTP_REQUEST);
	private boolean result;
	private Map<String, Object> res = new HashMap<String, Object>();
	
	// ���ѧ����Ϣ
	public String addStudentInfo(){	
		result = EditInfo.add(Integer.parseInt(request.getParameter("stu_id")), request.getParameter("stu_name"), request.getParameter("sex"), Integer.parseInt(request.getParameter("age")), request.getParameter("stu_class"), request.getParameter("phone"), request.getParameter("address"));
		if(!result){
			res.put("err", 1);
			res.put("msg", "���ʧ��");
		}else {
			 res.put("err", 0);
			 res.put("msg", "��ӳɹ�");
		 }
		return "success";
	
	}
	
	// ɾ��ѧ����Ϣ
	public String delStudentInfo(){
		// ���Ȩ�ޡ�Ȩ��ֵΪ2Ϊ����Ա�����Խ���ɾ��������ɾ��ʧ��
		if( ((Integer)session.get("authority")).equals(1)){
			res.put("err", 2);
			res.put("msg", "Ȩ�޲���");
			return "success";
		}
		EditInfo.delete(Integer.parseInt(request.getParameter("stu_id")));
		res.put("err", 0);
		res.put("msg", "ɾ���ɹ�");
//		 else return "success";
		return "success";
	}
	
	// ����ѧ����Ϣ
	public String updStudentInfo(){
		result = EditInfo.update(Integer.parseInt(request.getParameter("stu_id")), request.getParameter("stu_name"), request.getParameter("sex"), Integer.parseInt(request.getParameter("age")), request.getParameter("stu_class"), request.getParameter("phone"), request.getParameter("address"));
		if(!result){
			res.put("err", 3);
			res.put("msg", "�޸�ʧ��");
		}else {
			res.put("err", 0);
			res.put("msg", "�޸ĳɹ�");
		}
		return "success";
	}
	
	// �鿴����ѧ����Ϣ
	public String selStudentInfo(){
		res.put("msg", EditInfo.selectStu(Integer.parseInt(request.getParameter("stu_id"))));
		return "success";
	}

	// �鿴����ѧ����Ϣ
	public String selAllStudentInfo(){
		int auth = (Integer)session.get("authority");
		res.put("err", auth);
		res.put("msg",  EditInfo.selectAll());
		res.put("username", (String)session.get("username"));
		return "success";
	}

	public Map<String, Object> getRes() {
		return res;
	}

	public void setRes(Map<String, Object> res) {
		this.res = res;
	}
	
	
}
