/**
 *  ClassName: SecurityInterceptor.java
 *  created on 2010-6-9
 *  Copyrights 2010 qjyong All rights reserved.
 *  site: http://blog.csdn.net/qjyong
 *  email: qiujy@gmain.com
 */
package cn.soft.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * 权限拦截�?
 * @author qiujy
 */
public class SecurityInterceptor extends AbstractInterceptor {
	public static final String SECURITY_LOGIN = "security_login";
	public static final String WITHOUT_AUTHORITY = "without_authority";
	
	@Override
	public void init() {
		System.out.println("init security interceptor....");
	}

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		String result = "";
		String actionName = invocation.getProxy().getAction().getClass().getSimpleName();
		String method = invocation.getProxy().getMethod();
		System.out.println("actionName:" + actionName);
		System.out.println("methodName;" + method);
		return result;
	}
}
