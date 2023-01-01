package com.abhiram.productservice.Service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.abhiram.productservice.DTO.ProductRequest;
import com.abhiram.productservice.DTO.ProductResponse;
import com.abhiram.productservice.model.ProductTO;
import com.abhiram.productservice.repository.ProductRepo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ProductService {

	private final ProductRepo productRepo;

	public void createProduct(ProductRequest productRequest) {

		ProductTO productObj = ProductTO.builder().name(productRequest.getName())
				.descrpition(productRequest.getDescrpition()).price(productRequest.getPrice()).build();

		productRepo.save(productObj);
		log.info("Product with id {} is Creataed successfully", productObj.getAutoId());

	}

	public List<Object> getAllProduct() {

		List<ProductTO> productList = (List<ProductTO>) productRepo.findAll();
		return (List<Object>) productList.stream().map(new Function<ProductTO, Object>() {
			public Object apply(ProductTO product) {
				return mapToProductResponse(product);
			}
		}).collect(Collectors.toList());
	}

	private ProductResponse mapToProductResponse(ProductTO productTO) {
		return ProductResponse.builder().autoId(productTO.getAutoId()).name(productTO.getName())
				.descrpition(productTO.getDescrpition()).price(productTO.getPrice()).build();
	}

}
