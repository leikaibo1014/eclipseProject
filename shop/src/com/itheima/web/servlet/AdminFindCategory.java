package com.itheima.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itheima.domain.Category;
import com.itheima.service.AdminService;
import com.itheima.utils.BeanFactory;

/**
 * Servlet implementation class AdminFindCategory
 */
public class AdminFindCategory extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		AdminService service = (AdminService) BeanFactory.getBean("adminService");
		String cid = request.getParameter("cid");
		
		Category category = new Category();
		
		category  = service.findCategory(cid);
		
		request.setAttribute("category", category);
		
		request.getRequestDispatcher("/admin/category/edit.jsp").forward(request, response);
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
