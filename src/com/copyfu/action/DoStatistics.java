package com.copyfu.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.copyfu.dao.Statistics;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
// 对学生信息进行统计
public class DoStatistics extends ActionSupport{
	// 下面这样定义 res 使得传res的值可以是任意类型，以免再需要对后面数据处理
	private Map<String, Object> res = new HashMap<String, Object>();
	private List<Object[]> result = null;
	public String classPerson(){
		// 统计班级人数
		result = Statistics.classPerson();
		res.put("msg", result);
		return "success";
	}
	
	public String stuSex(){
		//统计学生性别
		result = Statistics.studentSex();
		res.put("msg", result);
		return "success";
	}
	
	public String stuAge(){
		// 统计学生年龄
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
