package com.example.ecommerce_api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerce_api.dto.ProductRequest;
import com.example.ecommerce_api.model.Product;
import com.example.ecommerce_api.service.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/products")
@Validated
public class ProductController {

	private final ProductService productService;

	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@Operation(summary = "Get all products")
	@GetMapping
	public ResponseEntity<List<Product>> getProducts() {
		List<Product> products = productService.getAllProducts();
		return ResponseEntity.ok(products);
	}

	@Operation(summary = "Add new 1 product")
	@PostMapping
	public ResponseEntity<Product> addProduct(@Valid @RequestBody ProductRequest productRequest) {
		Product product = new Product();
		product.setName(productRequest.getName());
		product.setPrice(productRequest.getPrice());
		Product createdProduct = productService.createProduct(product);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
	}

	@Operation(summary = "Update product by id")
	@PutMapping("/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable Long id, @Valid @RequestBody ProductRequest productRequest) {
        try {
            Product updatedProduct = new Product();
            updatedProduct.setName(productRequest.getName());
            updatedProduct.setPrice(productRequest.getPrice());
            Product result = productService.updateProduct(id, updatedProduct);
            return ResponseEntity.ok(result);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

	@Operation(summary = "Delete product by id")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
		try {
			productService.deleteProduct(id);
			return ResponseEntity.noContent().build(); // 204 No Content
		} catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@Operation(summary = "Search product by name")
	@GetMapping("/search")
	public ResponseEntity<List<Product>> searchProducts(@RequestParam String name) {
		List<Product> products = productService.searchProductsByName(name);
		return ResponseEntity.ok(products);
	}

}
