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
 * Servlet implementation class AdminDeleteCategory
 */
public class AdminDeleteCategory extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int is_delete = 0;
		String cid = request.getParameter("cid");
		AdminService service = (AdminService) BeanFactory.getBean("adminService");
		is_delete = service.DeleteCategory(cid);
		if(is_delete!=0){
			request.getRequestDispatcher("/findAllCategoryAdmin").forward(request, response);
		}else{
			System.out.println("删除失败");
			request.getRequestDispatcher("/findAllCategoryAdmin").forward(request, response);
		}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
