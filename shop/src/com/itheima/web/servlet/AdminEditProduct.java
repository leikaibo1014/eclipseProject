package com.itheima.web.servlet;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import com.itheima.domain.Category;
import com.itheima.domain.Product;
import com.itheima.service.AdminService;
import com.itheima.utils.BeanFactory;
import com.itheima.utils.CommonsUtils;

/**
 * Servlet implementation class AdminEditProduct
 */
public class AdminEditProduct extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		int is_update = 0;
		String pid = request.getParameter("pid");
		String pimage2 = request.getParameter("pimage2");
		
		Product product = new Product();		
		//收集数据的容器
		Map<String,Object> map = new HashMap<String,Object>();
		
		try {
			//创建磁盘文件项工厂
			DiskFileItemFactory factory = new DiskFileItemFactory();
			//创建文件上传核心对象
			ServletFileUpload upload = new ServletFileUpload(factory);
			//解析request获得文件项对象集合

			List<FileItem> parseRequest = upload.parseRequest(request);
			
			
			for(FileItem item : parseRequest){
				//判断是否是普通表单项
				boolean formField = item.isFormField();
				if(formField){
					//普通表单项 获得表单的数据 封装到Product实体中
					String fieldName = item.getFieldName();
					String fieldValue = item.getString("UTF-8");					
					
					map.put(fieldName, fieldValue);
					
				}else{
					//文件上传项 获得文件名称 获得文件的内容
					String fileName = item.getName();
					if(fileName.equals("")){
						map.put("pimage", pimage2);						
					}else{
					String date=new SimpleDateFormat("yyyyMMddHHmmss").format(Calendar.getInstance().getTime());  
                    fileName = date+"_"+fileName;  
                   
					String path = this.getServletContext().getRealPath("upload");
					InputStream in = item.getInputStream();
					OutputStream out = new FileOutputStream(path+"/"+fileName);//I:/xxx/xx/xxx/xxx.jpg
					IOUtils.copy(in, out);
					in.close();
					out.close();
					item.delete();
					
					map.put("pimage", "upload/"+fileName);
					
					}
					
				}
				
			}
			
			BeanUtils.populate(product, map);
			//是否product对象封装数据完全
			product.setPid(pid);
			
			if((map.get("is_hot").toString()).equals("0")){
				product.setPflag(0);
			}else{
				product.setPflag(1);
			}
						
			Category category = new Category();
			category.setCid(map.get("cid").toString());			
			product.setCategory(category);								
		
		//将product传递给service层
			AdminService service = (AdminService) BeanFactory.getBean("adminService");
			service.updateProductAdmin(product);	
			request.getRequestDispatcher("/findAllProduct").forward(request, response);	
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//直接跳转到商品页面
				
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
