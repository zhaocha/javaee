package com.copyfu.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.copyfu.dao.Statistics;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
// ��ѧ����Ϣ����ͳ��
public class DoStatistics extends ActionSupport{
	// ������������ res ʹ�ô�res��ֵ�������������ͣ���������Ҫ�Ժ������ݴ���
	private Map<String, Object> res = new HashMap<String, Object>();
	private List<Object[]> result = null;
	public String classPerson(){
		// ͳ�ư༶����
		result = Statistics.classPerson();
		res.put("msg", result);
		return "success";
	}
	
	public String stuSex(){
		//ͳ��ѧ���Ա�
		result = Statistics.studentSex();
		res.put("msg", result);
		return "success";
	}
	
	public String stuAge(){
		// ͳ��ѧ������
		result = Statistics.studentAge();
		res.put("msg", result);
		return "success";
	}
	
	public Map<String, Object> getRes() {
		return res;
	}
	public void setRes(Map<String, Object> res) {
		this.res = res;
	}
	
}
