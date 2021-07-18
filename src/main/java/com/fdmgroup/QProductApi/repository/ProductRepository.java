package com.fdmgroup.QProductApi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fdmgroup.QProductApi.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

	List<Product> findAllByName(String name);

	Product findByName(String name);
}
