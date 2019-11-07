package com.itheima.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itheima.service.AdminService;
import com.itheima.utils.BeanFactory;

/**
 * Servlet implementation class AdminDeleteProduct
 */
public class AdminDeleteProduct extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int is_delete = 0;
		String pid = request.getParameter("pid");
		
		AdminService service = (AdminService) BeanFactory.getBean("adminService");
		is_delete = service.DeleteProduct(pid);
		if(is_delete!=0){
			request.getRequestDispatcher("/findAllProduct").forward(request, response);
		}else{
			System.out.println("删除失败");
			request.getRequestDispatcher("/findAllProduct").forward(request, response);
		}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
