package com.abhiram.inventoryservice.repository;

import java.util.List;

import com.abhiram.inventoryservice.model.InventoryTO;

public interface CustomInventoryRepo {
	
	List<InventoryTO> findBySkuCodeIn(List<String> skuCode);

}
