package com.itheima.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.itheima.dao.AdminDao;
import com.itheima.domain.Category;
import com.itheima.domain.Order;
import com.itheima.domain.Product;

public interface AdminService {

	public List<Category> findAllCategory();

	public void saveProduct(Product product) throws SQLException;

	public List<Order> findAllOrders();

	public List<Map<String, Object>> findOrderInfoByOid(String oid);

	public List<Product> findAllProduct();

	public int DeleteCategory(String cid);

	public Category findCategory(String cid);

	public int updateCategory(String cid, String cname);

	public int DeleteProduct(String pid);

	public Product findProduct(String pid);

	public void updateProductAdmin(Product product)throws SQLException;

	
	
}
