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

//�����¼��Ϣ
public class Login extends ActionSupport  {
	private String username;
	private String password;
	private int authority;
	private Map<String, Object> res = new HashMap<String, Object>();
	public String execute(){
		//struts2�е� ActionContext ��HttpServletRequest��HttpSession��ServletContext�����˷�װ
		ActionContext context = ActionContext.getContext();
		// ����û��Ϸ���
		int result = CheckLogin.check(username,password,authority);
		if(result == 0){
			Map<String, Object> session = context.getSession();
			session.put("username", username);
			session.put("authority", authority);
			res.put("err", 0);
			res.put("username", username);
		}else if(result == 2){
			res.put("err", 2);
			res.put("msg", "�û�������");
		}else if(result == 3){
			res.put("err", 3);
			res.put("msg", "�������");
			
		}else{
			res.put("err", 1);
			res.put("msg", "δ֪����");
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
