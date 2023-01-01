package com.abhiram.orderrservice.Service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.abhiram.orderrservice.DTO.InventoryResponse;
import com.abhiram.orderrservice.DTO.OrderLineItemsDTO;
import com.abhiram.orderrservice.DTO.OrderRequest;
import com.abhiram.orderrservice.model.OrderLineTO;
import com.abhiram.orderrservice.model.OrderTO;
import com.abhiram.orderrservice.repository.OrderRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

	private final OrderRepo orderRepo;
	private final WebClient.Builder webClientBuilder;

	public String placeOrder(OrderRequest orderRequest) {

		OrderTO orderObj = new OrderTO();
		orderObj.setOrderNumber(UUID.randomUUID().toString());

		List<OrderLineTO> orderLineItems = orderRequest.getOrderList().stream().map(this::mapDTOtoObj)
				.collect(Collectors.toList());

		orderObj.setOrderList(orderLineItems);

		List<String> skuCodes = orderLineItems.stream().map(obj -> obj.getSkuCode()).collect(Collectors.toList());

		// Call Inventory Service and before place order check in stock or not
		InventoryResponse[] inventoryResponsArray = webClientBuilder.build().get()
				.uri("http://inventory-service/api/inventory/isInStock",
						uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
				//.accept(MediaType.APPLICATION_JSON) 
				.retrieve()
				.bodyToMono(InventoryResponse[].class)
				//.toEntity(InventoryResponse[].class)
				.block();
		
		
		boolean allProductsInStock =Arrays.stream(inventoryResponsArray).allMatch(InventoryResponse::isInStock);
		if (allProductsInStock && inventoryResponsArray.length>0) {
			orderRepo.save(orderObj);
            return "Order Placed Successfully";
        } else {
            throw new IllegalArgumentException("Product is not in stock, please try again later");
        }

	}

	private OrderLineTO mapDTOtoObj(OrderLineItemsDTO orderLineItemsDTO) {

		OrderLineTO lineToObj = new OrderLineTO();
		lineToObj.setPrice(orderLineItemsDTO.getPrice());
		lineToObj.setQuantity(orderLineItemsDTO.getQuantity());
		lineToObj.setSkuCode(orderLineItemsDTO.getSkuCode());
		return lineToObj;

	}

}
