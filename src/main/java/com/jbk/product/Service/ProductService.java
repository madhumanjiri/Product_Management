package com.jbk.product.Service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.jbk.product.Entity.Product;

public interface ProductService {

	public boolean saveProduct(Product product);

	public List<Product> getAllProduct();

	public Product getProductById(String productId);

	public boolean deleteProductById(String productId);

	public boolean updateProduct(Product product);

	public List<Product> sortProduct(String sortBy);
	
	public Product getMaxPriceProduct();
	
	public double sumOfProduct();
	
	public int getTotalCountOfProduct();
	
//	public String uploadSheet(MultipartFile file,HttpSession httpSession);
	
}
