package com.jbk.product.Dao;

import java.util.List;

import com.jbk.product.Entity.Product;

public interface ProductDao {

	public boolean saveProduct(Product product);

	public List<Product> getAllProduct();

	public Product getProductById(String productId);

	public boolean deleteProductById(String productId);

	public boolean updateProduct(Product product);
	


}
