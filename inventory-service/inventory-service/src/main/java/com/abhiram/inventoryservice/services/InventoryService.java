package com.abhiram.inventoryservice.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.abhiram.inventoryservice.DTO.InventoryResponse;
import com.abhiram.inventoryservice.model.InventoryTO;
import com.abhiram.inventoryservice.repository.CustomInventoryRepo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class InventoryService {

	@Autowired private  CustomInventoryRepo customInventoryRepository;

	@Transactional(readOnly = true)
	//@SneakyThrows
	public List<InventoryResponse> isInStock(List<String> skuCode) {

		log.info(":::::: Checking Inventory ::::::");
		List<InventoryTO> list = customInventoryRepository.findBySkuCodeIn(skuCode);
		List<InventoryResponse> resList = new ArrayList<InventoryResponse>();
		for(InventoryTO inventoryTO : list)
		{
			InventoryResponse o= new InventoryResponse();
					o.setSkuCode(inventoryTO.getSkuCode());
					o.setInStock(inventoryTO.getQuantity()> 0);
			resList.add(o);
			
		}
        return resList;
		/*return list.stream().map(inventoryTO -> InventoryResponse.builder()
						.skuCode(inventoryTO.getSkuCode()).isInStock(inventoryTO.getQuantity() > 0))
				.collect(Collectors.toList());*/

	}

}
