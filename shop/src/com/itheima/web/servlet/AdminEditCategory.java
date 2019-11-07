package com.itheima.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itheima.service.AdminService;
import com.itheima.utils.BeanFactory;

/**
 * Servlet implementation class AdminEditCategory
 */
public class AdminEditCategory extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int is_update = 0;
		String cid = request.getParameter("cid");
		String cname = request.getParameter("cname");
		
		AdminService service = (AdminService) BeanFactory.getBean("adminService");
		is_update = service.updateCategory(cid,cname);
		if(is_update!=0){
			request.getRequestDispatcher("/findAllCategoryAdmin").forward(request, response);
		}else{
			request.getRequestDispatcher("/findAllCategoryAdmin").forward(request, response);
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
