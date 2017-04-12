package com.news.backend.system.shiro;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.news.common.core.utils.ExceptionProcUtil;
import com.news.common.project.app.NewsAdminUserAppService;
import com.news.common.project.dto.NewsAdminUserDto;

/**
 * Shiro Realm 实现
 * @author zcy
 *
 */
public class MyShiroRealm extends AuthorizingRealm {
	private static Logger _log = LoggerFactory.getLogger(MyShiroRealm.class);
	@Autowired
	protected NewsAdminUserAppService newsAdminUserAppService;
	/**
	 * 授权信息
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		return null;
	}

	/**
	 * 认证信息
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;

		if (StringUtils.isNotBlank(token.getUsername())) {
			NewsAdminUserDto user;
			try {
				user = newsAdminUserAppService.getUserByName(token.getUsername());
				if (user != null) {
					return new SimpleAuthenticationInfo(user.getName(),user.getPassword(), getName());
				}
			} catch (Exception e) {
				_log.error("系统报错，报错原因：{}",ExceptionProcUtil.getExceptionDesc(e));
			}
		}
		return null;
	}

}
