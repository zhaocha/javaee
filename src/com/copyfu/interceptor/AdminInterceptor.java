package com.copyfu.interceptor;

import java.util.HashMap;
import java.util.Map;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

// 拦截器，对需要登录才能显示或处理的页面要先经过这里已验证是否已经登录
public class AdminInterceptor extends AbstractInterceptor {
	private Map<String, Object> errRes = new HashMap<String, Object>();
	public String intercept(ActionInvocation invocation) throws Exception{
		 Map<String, Object> session = invocation.getInvocationContext().getSession(); 
		 String login = (String)session.get("username");
		 if(login != null && login.length() > 0){
			 return invocation.invoke();
		 }else {
			 errRes.put("err", 1);
			 errRes.put("msg", "没有权限");
			 return "error";
		 }		 
	 }
	public Map<String, Object> getErrRes() {
		return errRes;
	}
	public void setErrRes(Map<String, Object> errRes) {
		this.errRes = errRes;
	}
	 
}
