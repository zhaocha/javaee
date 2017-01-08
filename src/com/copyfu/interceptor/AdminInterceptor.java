package com.copyfu.interceptor;

import java.util.HashMap;
import java.util.Map;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

// ������������Ҫ��¼������ʾ�����ҳ��Ҫ�Ⱦ�����������֤�Ƿ��Ѿ���¼
public class AdminInterceptor extends AbstractInterceptor {
	private Map<String, Object> errRes = new HashMap<String, Object>();
	public String intercept(ActionInvocation invocation) throws Exception{
		 Map<String, Object> session = invocation.getInvocationContext().getSession(); 
		 String login = (String)session.get("username");
		 if(login != null && login.length() > 0){
			 return invocation.invoke();
		 }else {
			 errRes.put("err", 1);
			 errRes.put("msg", "û��Ȩ��");
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
