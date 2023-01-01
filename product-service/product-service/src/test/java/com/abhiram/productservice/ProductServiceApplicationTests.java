package com.abhiram.productservice;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.abhiram.productservice.model.ProductTO;
import com.abhiram.productservice.repository.ProductRepo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
//@Testcontainers -- for Docker
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
class ProductServiceApplicationTests {

	@Autowired
	private MockMvc mockmvc;

	@Autowired
	private ProductRepo productRepository;

	@Disabled("Save TestCurrently disabled")
	@Test
	@Order(1)
	@DisplayName("TEST_1 :: create Product")
	public void createProduct() throws Exception {

		ProductTO productTestObj = ProductTO.builder().name("Iphone_13").descrpition("Apple Iphone 13").price(11.45)
				.build();

		String URI = "/api/products/saveProduct";
		String objectToString = mapToJson(productTestObj);
		RequestBuilder reqBuilder = MockMvcRequestBuilders.post(URI).accept(MediaType.APPLICATION_JSON)
				.content(objectToString).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockmvc.perform(reqBuilder).andReturn();
		MockHttpServletResponse mockResponse = result.getResponse();
		assertEquals(HttpStatus.CREATED.value(), mockResponse.getStatus());

	}

	@Test
	@Order(2)
	@DisplayName("TEST_2 :: getAllProduct")
	public void getAllProduct() throws Exception {

		String URI = "/api/products/getAllProduct";
		RequestBuilder reqBuilder = MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockmvc.perform(reqBuilder).andReturn();
		MockHttpServletResponse mockResponse = result.getResponse();
		assertEquals(HttpStatus.OK.value(), mockResponse.getStatus());
		assertEquals(9, productRepository.findAll().size());
	}

	private String mapToJson(Object obj) throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(obj);
	}

}
