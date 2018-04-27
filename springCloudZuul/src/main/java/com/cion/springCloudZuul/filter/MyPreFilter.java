package com.cion.springCloudZuul.filter;

import java.text.MessageFormat;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.cion.springCloudZuul.utils.MyConstants;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class MyPreFilter extends ZuulFilter {

	@Autowired
	private Environment environment;
	
	/**
	 * 自定义属性
	 */
	private static String upProperty = "zuul.routes.{0}.usernamepassword";
	
	@Override
	public Object run() throws ZuulException {
		RequestContext ctx = RequestContext.getCurrentContext();
		String userpassword = null;
		try {
			StringBuilder requestURI = new StringBuilder(ctx.getRequest().getRequestURI()).deleteCharAt(0);
			//考虑每个服务可能有不同密码
			userpassword = environment.getProperty(MessageFormat.format(upProperty, requestURI.substring(0, requestURI.indexOf("/"))));
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(userpassword == null || "".equals(userpassword)) {
				userpassword = MyConstants.DEFAULT_USER_PASS;
			}
		}
		//根据basic认证规则，添加Basic Auth认证信息，因为服务端开了认证
        ctx.addZuulRequestHeader("Authorization", "Basic " + getBase64Credentials(userpassword));
		return null;
	}
	/**
	 * 
	 * @param usernamepassword   username:password
	 * @return
	 */
	private String getBase64Credentials(String usernamepassword) {
        byte[] plainCredsBytes = usernamepassword.getBytes();
        byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
        return new String(base64CredsBytes);
    }
	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public int filterOrder() {
		return FilterConstants.PRE_DECORATION_FILTER_ORDER -1;
	}

	@Override
	public String filterType() {
		return FilterConstants.PRE_TYPE;
	}

}
