package com.example.ecommerce_api.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ProductRequest {

	@NotBlank(message = "not allow empty name")
	private String name;

	@NotNull(message = "not allow price null")
	@Min(value = 0, message = "price must be > 0")
	private Double price;

	// Getter, Setter
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

}
