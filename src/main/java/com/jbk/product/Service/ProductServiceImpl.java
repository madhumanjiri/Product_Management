package com.jbk.product.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.jbk.product.Dao.ProductDao;
import com.jbk.product.Entity.Product;
import com.jbk.product.Sort.ProductIdComaparator;
import com.jbk.product.Sort.ProductNameComparator;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDao dao;

	@Override
	public boolean saveProduct(Product product) {

		if (product.getProductId() == null) {
			String id = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new java.util.Date());
			product.setProductId(id);
		}
		boolean isAdded = dao.saveProduct(product);
		return isAdded;
	}

	@Override
	public List<Product> getAllProduct() {
		List<Product> list = dao.getAllProduct();
		return list;
	}

	@Override
	public Product getProductById(String productId) {
		Product product = dao.getProductById(productId);
		return product;
	}

	@Override
	public boolean deleteProductById(String productId) {
		boolean isDeleted = dao.deleteProductById(productId);
		return isDeleted;
	}

	@Override
	public boolean updateProduct(Product product) {
		boolean isUpdated = dao.updateProduct(product);
		return isUpdated;
	}

	@Override
	public List<Product> sortProduct(String sortBy) {

		List<Product> allProduct = getAllProduct();

		if (allProduct.size() > 1) {
			if (sortBy.equalsIgnoreCase("productId")) {
				Collections.sort(allProduct, new ProductIdComaparator());
			} else {
				Collections.sort(allProduct, new ProductNameComparator());
			}
		}

		return allProduct;
	}

	@Override
	public Product getMaxPriceProduct() {
		List<Product> allProducts = getAllProduct();
		Product product = null;

		if (!allProducts.isEmpty()) {
			product = allProducts.stream().max(Comparator.comparingDouble(Product::getProductPrice)).get();
		}
		return product;
	}

	@Override
	public double sumOfProduct() {
		List<Product> allProducts = getAllProduct();

		double sum = 0;
		if (!allProducts.isEmpty()) {
			sum = allProducts.stream().mapToDouble(Product::getProductPrice).sum();
		}
		return sum;
	}

	@Override
	public int getTotalCountOfProduct() {

		List<Product> allProducts = getAllProduct();

		int size = 0;
		if (!allProducts.isEmpty()) {
			size = allProducts.size();
		}
		return size;

	}

	/*
	 * @Override public String uploadSheet(MultipartFile file, HttpSession
	 * httpSession) {
	 * 
	 * String path = httpSession.getServletContext().getRealPath("/"); String
	 * filename = file.getOriginalFilename(); FileOutputStream fos = null; byte[]
	 * data = file.getBytes();
	 * 
	 * try { fos = new FileOutputStream(new File(path + File.separator + filename));
	 * fos.write(data);
	 * 
	 * } catch (Exception e) { e.printStackTrace(); } return null; }
	 */
}
