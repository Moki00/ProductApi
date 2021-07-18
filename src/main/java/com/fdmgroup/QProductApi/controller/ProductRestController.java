package com.fdmgroup.QProductApi.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fdmgroup.QProductApi.exception.ProductNotFoundException;
import com.fdmgroup.QProductApi.model.Product;
import com.fdmgroup.QProductApi.service.ProductService;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/v1/products")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductRestController {

	@Autowired
	private ProductService productService;

	@GetMapping
	public List<Product> getAllProducts() {
		return productService.findAllProducts();
	}

	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Got product from persistence", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "404", description = "Sorry, product not found", content = @Content) })
	@GetMapping("/{id}")
	public Product getProductById(@PathVariable("id") int id) throws ProductNotFoundException {
		if (productService.findProductById(id).isEmpty()) {
			throw new ProductNotFoundException("Product not found.");
		}
		return productService.findProductById(id).get();
	}

	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Added product from persistence", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "404", description = "Sorry, product not added", content = @Content) })
	@PostMapping
	public ResponseEntity<Product> addProduct(@RequestBody Product product) {
		productService.createProduct(product);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest() //
				.path("/{PRODUCT_ID}") // like /api/v1/products/123
				.buildAndExpand(product.getId()) //
				.toUri();
		System.out.println("Created resource at: " + location.toString());
		return ResponseEntity.created(location).build();
	}

	@PutMapping
	public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
		productService.updateProduct(product);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest() //
				.path("/{PRODUCT_ID}") //
				.buildAndExpand(product.getId()) //
				.toUri();
		System.out.println("Created resource at: " + location.toString());
		return ResponseEntity.created(location).build();
	}

	@DeleteMapping("/{id}")
	public void removeProductById(@PathVariable("id") int id) {
		productService.deleteProductById(id);
	}
}
