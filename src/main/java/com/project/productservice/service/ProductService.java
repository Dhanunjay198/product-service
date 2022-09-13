package com.project.productservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Range;
import org.springframework.stereotype.Service;

import com.project.productservice.dto.ProductDto;
import com.project.productservice.repository.ProductRepository;
import com.project.productservice.util.EntityDtoUtil;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repo;

	public Flux<ProductDto> getAllProducts() {
		return this.repo.findAll().map(EntityDtoUtil::toDto);

	}

	public Flux<ProductDto> getProductsBetweenPriceRange(int min, int max) {
		return this.repo.findProductsByPrice(Range.closed(min, max)).map(EntityDtoUtil::toDto);

	}
	public Mono<ProductDto> getProductById(String Id) {
		return this.repo.findById(Id).map(EntityDtoUtil::toDto);
	}

	public Mono<ProductDto> addProduct(Mono<ProductDto> productDtoMono) {
		return productDtoMono.map(EntityDtoUtil::toEntity).flatMap(this.repo::insert).map(EntityDtoUtil::toDto);
	}

	public Mono<ProductDto> updateProduct(String id, Mono<ProductDto> productDtoMono) {
		return this.repo.findById(id)
				.flatMap(p -> productDtoMono.map(EntityDtoUtil::toEntity).doOnNext(s -> s.setId(id)))
				.flatMap(this.repo::save).map(EntityDtoUtil::toDto);
	}

	public Mono<Void> deleteProductById(String id) {
		return this.repo.deleteById(id);
	}
}
