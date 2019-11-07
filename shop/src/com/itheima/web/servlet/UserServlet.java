package com.itheima.web.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;

import com.itheima.domain.User;
import com.itheima.service.UserService;
import com.itheima.utils.CommonsUtils;
import com.itheima.utils.MailUtils;

/**
 * Servlet implementation class UserServlet
 */
public class UserServlet extends BaseServlet {
	
	//1.激活验证servlet
	
	public void active(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获得激活码
				String activeCode = request.getParameter("activeCode");
				
				UserService service = new UserService();
				service.active(activeCode);
				
				//跳转到登录页面
				response.sendRedirect(request.getContextPath()+"/login.jsp");
		
	}
	
	
	//用户注册servlet
	public void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
request.setCharacterEncoding("UTF-8");
		
		//获得表单数据
		Map<String, String[]> properties = request.getParameterMap();
		User user = new User();
		try {
			//自己指定一个类型转换器（将String转成Date）
			ConvertUtils.register(new Converter() {
				@Override
				public Object convert(Class clazz, Object value) {
					//将string转成date
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
					Date parse = null;
					try {
						parse = format.parse(value.toString());
					} catch (ParseException e) {
						e.printStackTrace();
					}
					return parse;
				}
			}, Date.class);
			//映射封装
			BeanUtils.populate(user, properties);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		
		//private String uid;
		user.setUid(CommonsUtils.getUUID());
		//private String telephone;
		user.setTelephone(null);
		//private int state;//是否激活
		user.setState(0);
		//private String code;//激活码
		String activeCode = CommonsUtils.getUUID();
		user.setCode(activeCode);
		
		
		//将user传递给service层
		UserService service = new UserService();
		boolean isRegisterSuccess = service.regist(user);
		
		//是否注册成功
		if(isRegisterSuccess){
			//发送激活邮件
			/*String emailMsg = "亲爱的用户您好！  您收到这封这封电子邮件是因为您在我们网站注册了一个新的用户 (也可能是某人冒充您的名义) 。   假如这不是您本人所申请, 请不用理会这封电子邮件, 但是如果您持续收到这类的信件骚扰, 请您尽快联络管理员。       如果是你本人操作， "+"恭喜你注册成功，请点击下面的连接进行账户激活，然后才可以开始使用"
					+ "<a href='http://localhost:8080/shop/user?method=active&activeCode="+activeCode+"'>"
							+ "http://localhost:8080/shop/user?method=active&activeCode="+activeCode+"</a>";*/
			
			
			String emailMsg = "亲爱的用户您好！  您收到这封这封电子邮件是因为您在我们网站注册了一个新的用户 (也可能是某人冒充您的名义) 。   假如这不是您本人所申请, 请不用理会这封电子邮件, 但是如果您持续收到这类的信件骚扰, 请您尽快联络管理员。       如果是你本人操作， "+"恭喜你注册成功，请点击下面的连接进行账户激活，然后才可以开始使用"
					+ "<a href='http://120.79.42.240:8080/shop/user?method=active&activeCode="+activeCode+"'>"
							+ "http://120.79.42.240:8080/shop/user?method=active&activeCode="+activeCode+"</a>";
			
			try {
				MailUtils.sendMail(user.getEmail(), emailMsg);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			//跳转到注册成功页面
			response.sendRedirect(request.getContextPath()+"/registerSuccess.jsp");
		}else{
			//跳转到失败的提示页面
			response.sendRedirect(request.getContextPath()+"/registerFail.jsp");
		}
		
	}
	
	//注册时用户名校验servlet
	public void checkUsername(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获得用户名
				String username = request.getParameter("username");
				
				UserService service = new UserService();
				boolean isExist = service.checkUsername(username);
				
				String json = "{\"isExist\":"+isExist+"}";
				
				response.getWriter().write(json);
		
	}
	
	//用户登录
		public void login(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			HttpSession session = request.getSession();

			//获得输入的用户名和密码
			String username = request.getParameter("username");
			String password = request.getParameter("password");

			//对密码进行加密
			//password = MD5Utils.md5(password);

			//将用户名和密码传递给service层
			UserService service = new UserService();
			User user = null;
			try {
				user = service.login(username,password);
			} catch (Exception e) {
				e.printStackTrace();
			}

			//判断用户是否登录成功 user是否是null
			if(user!=null){
				//登录成功
				//***************判断用户是否勾选了自动登录*****************
				String autoLogin = request.getParameter("autoLogin");
				if("true".equals(autoLogin)){
					//要自动登录
					//创建存储用户名的cookie
					Cookie cookie_username = new Cookie("cookie_username",user.getUsername());
					cookie_username.setMaxAge(10*60);
					//创建存储密码的cookie
					Cookie cookie_password = new Cookie("cookie_password",user.getPassword());
					cookie_password.setMaxAge(10*60);

					response.addCookie(cookie_username);
					response.addCookie(cookie_password);

				}

				//***************************************************
				//将user对象存到session中
				session.setAttribute("user", user);

				//重定向到首页
				response.sendRedirect(request.getContextPath()+"/index.jsp");
			}else{
				request.setAttribute("loginError", "用户名或密码错误");
				request.getRequestDispatcher("/login.jsp").forward(request, response);
			}
		}
	
	
		//用户注销
		public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException{
			HttpSession session = request.getSession();
			//从session中将user删除
			session.removeAttribute("user");
			
			//将存储在客户端的cookie删除掉
			Cookie cookie_username = new Cookie("cookie_username","");
			cookie_username.setMaxAge(0);
			//创建存储密码的cookie
			Cookie cookie_password = new Cookie("cookie_password","");
			cookie_password.setMaxAge(0);

			response.addCookie(cookie_username);
			response.addCookie(cookie_password);
			
			
			response.sendRedirect(request.getContextPath()+"/login.jsp");
			
		}	
	
	
	
	
}
