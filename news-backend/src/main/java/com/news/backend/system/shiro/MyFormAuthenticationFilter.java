package com.news.backend.system.shiro;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.news.backend.system.shiro.exception.UserNotFoundException;
import com.news.common.core.utils.ExceptionProcUtil;
import com.news.common.core.utils.SessionKey;
import com.news.common.project.app.NewsAdminUserAppService;
import com.news.common.project.dto.NewsAdminUserDto;
import com.news.common.project.exception.CheckedException;

public class MyFormAuthenticationFilter extends FormAuthenticationFilter {
	private static Logger log = LoggerFactory.getLogger(MyFormAuthenticationFilter.class);
	public static final String NEW_SESSION_INDICATOR = "com.news.backend.system.shiro.CaptchaFormAuthenticationFilter"; 
	public static final String DEFAULT_CAPTCHA_PARAM = "captcha";
	protected String captchaParam = DEFAULT_CAPTCHA_PARAM;
	
	@Autowired
	protected NewsAdminUserAppService newsAdminUserAppService;

	protected UsernamePasswordToken createToken(ServletRequest request,
			ServletResponse response) {
		String username = getUsername(request);
		String password = getPassword(request);
		boolean rememberMe = isRememberMe(request);
		String host = getHost(request);

		return new UsernamePasswordToken(username, password, rememberMe,host);
	}
	
	protected void doBusinessValidate(NewsAdminUserDto user) 
			throws UserNotFoundException {
		if (user == null) {
			throw new UserNotFoundException("用户名或密码错误");
		}
	}

	/**
	 * 获取用户
	 * 
	 * @param username
	 * @return
	 * @throws CbsCheckedException 
	 */
	protected NewsAdminUserDto fetchUser(String username) throws CheckedException {
		if (username == null) {
			return null;
		}
		try {
			return newsAdminUserAppService.getUserByName(username);
		} catch (Exception e) {
			log.error("获取用户信息失败：" +ExceptionProcUtil.getExceptionDesc(e));
			throw new CheckedException("","获取用户信息失败");
		}
	}

	
	@SuppressWarnings("rawtypes")
	private ServletRequest before(ServletRequest servletRequest,ServletResponse response)
	{
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		if (request.getSession() != null&&request.getMethod().equals("POST") ) {  
            System.out.println("getSession().getId()"+ request.getSession().getId());  
           /**session***/
           
            //--------复制 session到临时变量  
            HttpSession session = request.getSession();  
            HashMap<Object,Object> old = new HashMap<Object,Object>();  
            Enumeration keys = (Enumeration) session.getAttributeNames();                 
            while (keys.hasMoreElements()){  
                String key = (String) keys.nextElement();  
                if (!NEW_SESSION_INDICATOR.equals(key)){  
                    old.put(key, session.getAttribute(key));  
                    session.removeAttribute(key);  
                }  
            }    
            
           // session.invalidate();  
            SecurityUtils.getSubject().logout();
            session=request.getSession(true);   
            
            System.out.println("new Session:" + session.getId());  
 
          //-----------------复制session  
            for (Iterator it = old.entrySet().iterator(); it.hasNext();) {  
                Map.Entry entry = (Entry) it.next();  
                session.setAttribute((String) entry.getKey(), entry.getValue());  
            }  
         
        } 
		return servletRequest;
	}
	protected boolean executeLogin(ServletRequest request,
			ServletResponse response) throws Exception {
		before(request, response);
		UsernamePasswordToken token = createToken(request, response);
		if (token == null) {
			String msg = "方法createToken()返回空值，登录操作必须获得非空的AuthenticationToken！";
			throw new IllegalStateException(msg);
		}


		NewsAdminUserDto user = null;
		try {
			user = fetchUser(token.getUsername());
			doBusinessValidate(user);
			Subject subject = getSubject(request, response);
			subject.login(token);
			//登录成功
			writeUserToSession(user, (HttpServletRequest) request);
			return onLoginSuccess(token, subject, request, response);
		} catch (UserNotFoundException e) {
			return onLoginFailure(token, e, request, response);
		}  catch (AuthenticationException e) {
			return onLoginFailure(token, e, request, response);
		} catch(CheckedException e){
			return onLoginFailure(token, new AuthenticationException(e.getMessage()), request, response);
		}  catch(Exception e){
			return onLoginFailure(token, new AuthenticationException("登录异常",e), request, response);
		}
	}
	
	@Override
	protected boolean onLoginFailure(AuthenticationToken token,
			AuthenticationException e, ServletRequest request,
			ServletResponse response) {
		log.error("系统报错，报错原因：{}",e.getMessage());
		
		return super.onLoginFailure(token, e, request, response);
	}

	/**
	 * 将用户写入Session
	 * @param account
	 * @param request
	 * @author LS
	 * @date 2013-8-1
	 */
	protected void writeUserToSession(NewsAdminUserDto account, HttpServletRequest request) {
		request.getSession().setAttribute(SessionKey.LOGIN_ACCOUNT, account);
	}
	
	protected NewsAdminUserDto getSessionUser(HttpServletRequest request) {
		return (NewsAdminUserDto)request.getSession().getAttribute(SessionKey.LOGIN_ACCOUNT);
	}

	@Override
	protected void saveRequestAndRedirectToLogin(ServletRequest request,
			ServletResponse response) throws IOException {
		redirectToLogin(request, response);
	}

	protected void setFailureAttribute(ServletRequest request,
			AuthenticationException ae) {
		request.setAttribute(getFailureKeyAttribute(), ae);
	}
}
