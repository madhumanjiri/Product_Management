package com.jbk.product.Controller;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder.In;
import javax.servlet.http.HttpServlet;
import javax.swing.plaf.multi.MultiButtonUI;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.jbk.product.Entity.Product;
import com.jbk.product.Service.ProductService;
import com.jbk.productException.ProductNotFoundException;

@RestController
public class ProductController {

	@Autowired
	private ProductService service;

	@PostMapping(value = "/saveProduct")
	public ResponseEntity<Boolean> saveProduct(@Valid @RequestBody Product product) {
		boolean isAdded = service.saveProduct(product);
		if (isAdded) {
			return new ResponseEntity<Boolean>(isAdded, HttpStatus.OK);
		} else {
			return new ResponseEntity<Boolean>(isAdded, HttpStatus.NO_CONTENT);
		}

	}

	@GetMapping(value = "/getAllProduct")
	public ResponseEntity<List<Product>> getAllProduct() {
		List<Product> allProduct = service.getAllProduct();
		if (!allProduct.isEmpty()) {
			return new ResponseEntity<List<Product>>(allProduct, HttpStatus.OK);
		} else {
			throw new ProductNotFoundException("Products Not Found");
		}

	}

	@GetMapping(value = "/getProductById")
	public ResponseEntity<Product> getProductById(@PathVariable String product_Id) {

		Product product = service.getProductById(product_Id);
		if (product != null) {
			return new ResponseEntity<Product>(product, HttpStatus.OK);

		} else {
			throw new ProductNotFoundException("Product Not Found For Id: " + product_Id);
		}

	}

	@GetMapping(value = "/deleteProductById")
	public ResponseEntity<Boolean> deleteProductById(@PathVariable String productId) {

		boolean isDeleted = service.deleteProductById(productId);

		if (isDeleted) {
			return new ResponseEntity<Boolean>(isDeleted, HttpStatus.OK);
		} else {
			throw new ProductNotFoundException("Product Not Found For Id: " + productId);
		}
	}

	@PutMapping(value = "/updateProduct")
	public ResponseEntity<Boolean> updateProduct(@RequestBody Product product) {

		boolean isUpdated = service.updateProduct(product);

		if (isUpdated) {

			return new ResponseEntity<Boolean>(isUpdated, HttpStatus.OK);

		} else {

			throw new ProductNotFoundException("Product Not Found For Id: " + product.getProductId());
		}

	}

	@GetMapping(value = "/sortProduct")
	public ResponseEntity<List<Product>> sortProduct(@RequestParam String sortBy) {
		List<Product> sortedProduct = service.sortProduct(sortBy);

		if (!sortedProduct.isEmpty()) {
			return new ResponseEntity<List<Product>>(sortedProduct, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<Product>>(sortedProduct, HttpStatus.NO_CONTENT);
		}

	}

	@GetMapping(value = "/getMaxPriceProduct")
	public ResponseEntity<Product> getMaxPriceProduct() {
		Product product = service.getMaxPriceProduct();
		if (product != null) {
			return new ResponseEntity<Product>(product, HttpStatus.OK);
		} else {
			return new ResponseEntity<Product>(product, HttpStatus.NO_CONTENT);
		}

	}

	@GetMapping(value = "/sumOfProductPrice")
	public ResponseEntity<Double> sumOfProductPrice() {
		double sum = service.sumOfProduct();

		if (sum > 0) {
			return new ResponseEntity<Double>(sum, HttpStatus.OK);
		} else {
			return new ResponseEntity<Double>(sum, HttpStatus.NO_CONTENT);
		}

	}

	@GetMapping(value = "/getTotalCountOfProduct")
	public ResponseEntity<Integer> getTotalCountOfProduct() {
		int count = service.getTotalCountOfProduct();
		if (count > 0) {
			return new ResponseEntity<Integer>(count, HttpStatus.OK);
		} else {
			return new ResponseEntity<Integer>(count, HttpStatus.NO_CONTENT);
		}
	}

	/*
	 * @PostMapping(value = "/uploadSheet") public ResponseEntity<String>
	 * uploadSheet(@RequestPart MultipartFile file, HttpServlet servlet) {
	 * 
	 * 
	 * return null; }
	 */
}
