package com.example.ecommerce_api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.ecommerce_api.model.Product;
import com.example.ecommerce_api.repository.ProductRepository;

@Service
public class ProductService {

	private final ProductRepository productRepository;

	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	public Product createProduct(Product product) {
		return productRepository.save(product);
	}

	public Product updateProduct(Long id, Product updatedProduct) {
		Optional<Product> existingProductOpt = productRepository.findById(id);
		if (existingProductOpt.isPresent()) {
			Product existingProduct = existingProductOpt.get();
			existingProduct.setName(updatedProduct.getName());
			existingProduct.setPrice(updatedProduct.getPrice());
			return productRepository.save(existingProduct);
		} else {
			throw new RuntimeException("Product not found with id: " + id);
		}
	}

	public void deleteProduct(Long id) {
		if (productRepository.existsById(id)) {
			productRepository.deleteById(id);
		} else {
			throw new RuntimeException("Product not found with id: " + id);
		}
	}

	public List<Product> searchProductsByName(String name) {
		return productRepository.findByNameContainingIgnoreCase(name);
	}
}
