package com.project.productservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.productservice.dto.ProductDto;
import com.project.productservice.service.ProductService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping("/allProducts")

	public Flux<ProductDto> getAllProducts() {
		return this.productService.getAllProducts();
	}

	@GetMapping("/priceRange")

	public Flux<ProductDto> getAllProductsInRange(@RequestParam("min") int min, @RequestParam("max") int max) {
		return this.productService.getProductsBetweenPriceRange(min, max);
	}

	@GetMapping("/product/{id}")

	public Mono<ResponseEntity<ProductDto>> findProductById(@PathVariable String id) {
		return this.productService.getProductById(id).map(ResponseEntity::ok)
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}

	@PostMapping("/addProduct")
	public Mono<ProductDto> addProduct(@RequestBody Mono<ProductDto> productDtoMono) {
		return this.productService.addProduct(productDtoMono);
	}

	@PutMapping("/updateProduct/{id}")

	public Mono<ResponseEntity<ProductDto>> updateProduct(@PathVariable String id,
			@RequestBody Mono<ProductDto> productDtoMono) {
		return this.productService.updateProduct(id, productDtoMono).map(ResponseEntity::ok)
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}

	@DeleteMapping("/deleteProduct/{id}")

	public Mono<Void> deleteById(@PathVariable String id) {
		return this.productService.deleteProductById(id);
	}

}
