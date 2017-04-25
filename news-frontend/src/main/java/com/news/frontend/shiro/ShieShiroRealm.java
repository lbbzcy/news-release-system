package com.news.frontend.shiro;

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
import com.news.common.project.app.NewsUserAppService;
import com.news.common.project.dto.NewsUserDto;

public class ShieShiroRealm extends AuthorizingRealm {
	private static Logger _log = LoggerFactory.getLogger(ShieShiroRealm.class);
	@Autowired
	protected NewsUserAppService newsUserAppService;
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection authcToken) {
		return null;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;

		if (StringUtils.isNotBlank(token.getUsername())) {
			NewsUserDto user;
			try {
				user = newsUserAppService.getUserByAuth(token.getUsername());
				if (user != null) {
					return new SimpleAuthenticationInfo(user.getUsername(),user.getPassword(), getName());
				}
			} catch (Exception e) {
				_log.error("系统报错，报错原因：{}",ExceptionProcUtil.getExceptionDesc(e));
			}
		}
		return null;
	}

}
