package com.project.productservice.util;

import org.springframework.beans.BeanUtils;

import com.project.productservice.dto.ProductDto;
import com.project.productservice.entity.Product;

public class EntityDtoUtil {

	public static ProductDto toDto(Product product) {
		ProductDto dto = new ProductDto();
		BeanUtils.copyProperties(product, dto);

		return dto;
	}

	public static Product toEntity(ProductDto dto) {
		Product product = new Product();
		BeanUtils.copyProperties(dto, product);

		return product;
	}
}
