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
 * Servlet implementation class AdminfindAllCategory
 */
public class AdminfindAllCategory extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//提供一个List<Category> 转成json字符串
		request.setCharacterEncoding("UTF-8");		
		AdminService service = (AdminService) BeanFactory.getBean("adminService");
				List<Category> categoryList2 = service.findAllCategory();
				
				request.setAttribute("categoryList2", categoryList2);
				
				request.getRequestDispatcher("/admin/category/list.jsp").forward(request, response);
								
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
