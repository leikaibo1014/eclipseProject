package com.itheima.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itheima.domain.Category;
import com.itheima.domain.Product;
import com.itheima.service.AdminService;
import com.itheima.utils.BeanFactory;
import com.itheima.utils.PageBean;

/**
 * Servlet implementation class AdminfindProduct
 */
public class AdminfindProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		AdminService service = (AdminService) BeanFactory.getBean("adminService");
		String pid = request.getParameter("pid");
		Product product = new Product();
		
		product  = service.findProduct(pid);
		
		request.setAttribute("productAdmin", product);
		
		
		PageBean pageBean = new PageBean<>();
		pageBean.setCurrentCount(20);
		pageBean.setCurrentPage(1);
		
		
		request.setAttribute("pageBean", pageBean);
		
		request.getRequestDispatcher("/admin/product/edit.jsp").forward(request, response);
		
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
