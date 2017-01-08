package com.copyfu.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

import com.copyfu.dao.CheckLogin;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

//处理登录信息
public class Login extends ActionSupport  {
	private String username;
	private String password;
	private int authority;
	private Map<String, Object> res = new HashMap<String, Object>();
	public String execute(){
		//struts2中的 ActionContext 对HttpServletRequest、HttpSession和ServletContext进行了封装
		ActionContext context = ActionContext.getContext();
		// 检查用户合法性
		int result = CheckLogin.check(username,password,authority);
		if(result == 0){
			Map<String, Object> session = context.getSession();
			session.put("username", username);
			session.put("authority", authority);
			res.put("err", 0);
			res.put("username", username);
		}else if(result == 2){
			res.put("err", 2);
			res.put("msg", "用户不存在");
		}else if(result == 3){
			res.put("err", 3);
			res.put("msg", "密码错误");
			
		}else{
			res.put("err", 1);
			res.put("msg", "未知错误");
		}
		return "success";
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getAuthority() {
		return authority;
	}
	public void setAuthority(int authority) {
		this.authority = authority;
	}
	public Map<String, Object> getRes() {
		return res;
	}
	public void setRes(Map<String, Object> res) {
		this.res = res;
	}
}
