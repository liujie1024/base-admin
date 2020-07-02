package com.mb.security;

import java.util.Collection;
import java.util.Iterator;

import javax.annotation.Resource;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

import com.mb.menu.dao.MenuDao;
import com.mb.role.dao.RoleDao;

/**
 * 资源和权限列表
 */
public class MyInvocationSecurityMetadataSourceService implements FilterInvocationSecurityMetadataSource {

	@Resource
	private RoleDao roleDao;

	@Resource
	private MenuDao menuDao;

	
	// 返回所请求资源所需要的权限
	@Override
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
		// object 是一个URL，被用户请求的url。
		String url = ((FilterInvocation) object).getRequestUrl();
		System.out.println("url" + url);
		int firstQuestionMarkIndex = url.indexOf("?");
		if (firstQuestionMarkIndex != -1) {
			url = url.substring(0, firstQuestionMarkIndex);
		}
		Iterator<String> ite = MyUserDetailsService.resourceMap.keySet().iterator();
		while (ite.hasNext()) {
			String resURL = ite.next();
			if (url.equals(resURL)) {
				return MyUserDetailsService.resourceMap.get(resURL);
			}
		}
		return null;
	}

	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}

}
