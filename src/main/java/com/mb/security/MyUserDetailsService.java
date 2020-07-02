package com.mb.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.mb.login.dao.UserDao;
import com.mb.menu.dao.MenuDao;
import com.mb.menu.entity.Menu;
import com.mb.role.dao.RoleDao;
import com.mb.role.entity.Role;

/**
 * 用户登录的认证
 */
public class MyUserDetailsService implements UserDetailsService {

	@Resource
	private UserDao userDao;// 用户

	@Resource
	private RoleDao roleDao;// 角色

	@Resource
	private MenuDao menuDao;// 菜单

	public static Map<String, Collection<ConfigAttribute>> resourceMap = null;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Collection<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
		com.mb.login.entity.User user = userDao.getUserByName(username);// 本地数据库中的用户
		if (null == user) {
//			throw new UsernameNotFoundException("用户名不存在", username);
		}
		List<Role> roleList = roleDao.getRolesByUsername(username);// 更加用户名称查询角色
		for (Role role : roleList) {
			SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.getId());
			auths.add(authority);
		}
		this.loadResourceDefine();
		boolean enables = true;
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;
		return new User(user.getUsername(), user.getPassword(), enables, accountNonExpired, credentialsNonExpired, accountNonLocked, auths);
	}

	// 在Web服务器启动时，提取系统中的所有权限
	private void loadResourceDefine() {
		// 获得所有的角色
		List<Role> roleList = roleDao.getAllRole();
		// 应当是资源为key， 权限为value。 资源通常为url， 权限就是那些以ROLE_为前缀的角色。 一个资源可以由多个权限来访问。
		resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
		for (Role role : roleList) {
			String roleId = role.getId();
			ConfigAttribute ca = new SecurityConfig(roleId);
			List<Menu> menuList = menuDao.getMenuListByRoleId(roleId);// 根据角色id查询对应的菜单
			for (Menu menu : menuList) {
				String url = menu.getMenuurl();
				// 判断资源文件和权限的对应关系，如果已经存在相关的资源url，则要通过该url为key提取出权限集合，将权限增加到权限集合中。
				if (resourceMap.containsKey(url)) {
					Collection<ConfigAttribute> value = resourceMap.get(url);
					value.add(ca);
					resourceMap.put(url, value);
				} else {
					Collection<ConfigAttribute> atts = new ArrayList<ConfigAttribute>();
					atts.add(ca);
					resourceMap.put(url, atts);
				}
			}
		}
	}

}