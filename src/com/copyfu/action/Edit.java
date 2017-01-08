package com.copyfu.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.copyfu.dao.EditInfo;
import com.copyfu.entity.Student;
import com.opensymphony.xwork2.ActionContext;

// 处理对学生信息的增删查改
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
	// 开始不知道struts2 中可以直接获取前端传来的值，以为要用到request，就懒得改了
	private HttpServletRequest request = (HttpServletRequest)context.get(ServletActionContext.HTTP_REQUEST);
	private boolean result;
	private Map<String, Object> res = new HashMap<String, Object>();
	
	// 添加学生信息
	public String addStudentInfo(){	
		result = EditInfo.add(Integer.parseInt(request.getParameter("stu_id")), request.getParameter("stu_name"), request.getParameter("sex"), Integer.parseInt(request.getParameter("age")), request.getParameter("stu_class"), request.getParameter("phone"), request.getParameter("address"));
		if(!result){
			res.put("err", 1);
			res.put("msg", "添加失败");
		}else {
			 res.put("err", 0);
			 res.put("msg", "添加成功");
		 }
		return "success";
	
	}
	
	// 删除学生信息
	public String delStudentInfo(){
		// 检查权限。权限值为2为管理员，可以进行删除，否则删除失败
		if( ((Integer)session.get("authority")).equals(1)){
			res.put("err", 2);
			res.put("msg", "权限不足");
			return "success";
		}
		EditInfo.delete(Integer.parseInt(request.getParameter("stu_id")));
		res.put("err", 0);
		res.put("msg", "删除成功");
//		 else return "success";
		return "success";
	}
	
	// 更新学生信息
	public String updStudentInfo(){
		result = EditInfo.update(Integer.parseInt(request.getParameter("stu_id")), request.getParameter("stu_name"), request.getParameter("sex"), Integer.parseInt(request.getParameter("age")), request.getParameter("stu_class"), request.getParameter("phone"), request.getParameter("address"));
		if(!result){
			res.put("err", 3);
			res.put("msg", "修改失败");
		}else {
			res.put("err", 0);
			res.put("msg", "修改成功");
		}
		return "success";
	}
	
	// 查看单个学生信息
	public String selStudentInfo(){
		res.put("msg", EditInfo.selectStu(Integer.parseInt(request.getParameter("stu_id"))));
		return "success";
	}

	// 查看所有学生信息
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
