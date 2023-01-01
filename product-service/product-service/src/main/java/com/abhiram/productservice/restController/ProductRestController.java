package com.abhiram.productservice.restController;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.abhiram.productservice.DTO.ProductRequest;
import com.abhiram.productservice.DTO.ProductResponse;
import com.abhiram.productservice.Service.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductRestController {
	
	
	private final ProductService productService;
	
	
	@PostMapping("/saveProduct")
	@ResponseStatus(HttpStatus.CREATED)
	public void saveProduct(@RequestBody ProductRequest productRequest)
	{
		productService.createProduct(productRequest);
		
	}
	
	
	@GetMapping("/getAllProduct")
	@ResponseStatus(HttpStatus.OK)
	public  List<Object> getAllProduct()
	{
		return productService.getAllProduct();
		
	}

}
