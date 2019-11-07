package com.itheima.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.itheima.dao.AdminDao;
import com.itheima.domain.Category;
import com.itheima.domain.Order;
import com.itheima.domain.Product;
import com.itheima.service.AdminService;

public class AdminServiceImpl implements AdminService{

	public List<Category> findAllCategory() {
		AdminDao dao = new AdminDao();
		try {
			return dao.findAllCategory();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public void saveProduct(Product product) throws SQLException {
		AdminDao dao = new AdminDao();
		dao.saveProduct(product);
	}

	public List<Order> findAllOrders() {
		AdminDao dao = new AdminDao();
		List<Order> ordersList = null;
		try {
			ordersList = dao.findAllOrders();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ordersList;
	}

	public List<Map<String, Object>> findOrderInfoByOid(String oid) {
		AdminDao dao = new AdminDao();
		List<Map<String, Object>> mapList = null;
		try {
			mapList = dao.findOrderInfoByOid(oid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mapList;
	}


	public List<Product> findAllProduct() {
		AdminDao dao = new AdminDao();
		List<Product> productlist = null;
		try {
			productlist = dao.findAllProduct();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return productlist;
	}

	//删除分类
	@Override
	public int DeleteCategory(String cid) {
		AdminDao dao = new AdminDao();
		int is_delete = 0 ;
		try {
			is_delete = dao.AdmindeleteCategory(cid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return is_delete;
	}

	@Override
	public Category findCategory(String cid) {
		AdminDao dao = new AdminDao();
		try {
			return dao.findCategory(cid);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int updateCategory(String cid, String cname) {
		AdminDao dao = new AdminDao();
		int is_update = 0 ;
		try {
			is_update = dao.AdminupdateCategory(cid,cname);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return is_update;
	}

	@Override
	public int DeleteProduct(String pid) {
		AdminDao dao = new AdminDao();
		int is_delete = 0 ;
		try {
			is_delete = dao.AdmindeleteProduct(pid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return is_delete;
	}

	@Override
	public Product findProduct(String pid) {
		AdminDao dao = new AdminDao();
		try {
			return dao.findProduct(pid);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void updateProductAdmin(Product product) throws SQLException {
		AdminDao dao = new AdminDao();
		dao.updateProductAdmin(product);
		
	}

}
