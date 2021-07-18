package com.fdmgroup.QProductApi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.QProductApi.model.Product;
import com.fdmgroup.QProductApi.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepo;

	public void createProduct(Product product) {
		productRepo.save(product);
	}

	public List<Product> findAllProducts() {
		return productRepo.findAll();
	}

	public Optional<Product> findProductById(int id) {
		return productRepo.findById(id);
	}

	public List<Product> getProductsByName(String name) {
		return productRepo.findAllByName(name);
	}
	
	public Product getProductByName(String name) {
		return productRepo.findByName(name);
	}

	public void updateProduct(Product product) {
		productRepo.save(product);
	}

	public void deleteProductById(int id) {
		productRepo.deleteById(id);
	}
}
