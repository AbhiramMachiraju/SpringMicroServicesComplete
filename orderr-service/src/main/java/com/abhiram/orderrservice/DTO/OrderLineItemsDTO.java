package com.abhiram.orderrservice.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderLineItemsDTO {
	
	private String skuCode;
	private Double price;
	private int quantity;

}
