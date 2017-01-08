package com.copyfu.action;

import java.util.HashMap;
import java.util.Map;

import com.copyfu.dao.Register;
import com.opensymphony.xwork2.ActionSupport;
// 处理注册
public class DoRegister extends ActionSupport {
	private String username;
	private String password;
	private String repassword;
	private Map<String, Object> res = new HashMap<String, Object>();
	public String execute(){
		int result = Register.register(username, password, repassword);
		if(result == 0) {
			res.put("err", 0);
			res.put("msg", "注册成功");
		}else if(result == 2){
			res.put("err", 2);
			res.put("msg", "用户已存在");
		}else if(result == 3){
			res.put("err", 3);
			res.put("msg", "输入的两次密码不相同");
		}else{
			res.put("err", 1);
			res.put("msg", "异常");
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
	public String getRepassword() {
		return repassword;
	}
	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}
	public Map<String, Object> getRes() {
		return res;
	}
	public void setRes(Map<String, Object> res) {
		this.res = res;
	}
	
}
