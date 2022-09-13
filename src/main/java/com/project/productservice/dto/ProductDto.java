package com.project.productservice.dto;

import lombok.Data;


@Data

public class ProductDto {

	private String description;
	private String id;
	private int price;



	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "ProductDto [description=" + description + ", id=" + id + ", price=" + price + "]";
	}

}
