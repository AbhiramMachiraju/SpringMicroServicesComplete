package com.abhiram.productservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.abhiram.productservice.model.ProductTO;

@Repository
public interface ProductRepo extends JpaRepository<ProductTO, Long> {
	
	
	  

}
