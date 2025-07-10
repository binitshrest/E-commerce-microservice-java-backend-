package com.binitshrestha.inventory_service;

import com.binitshrestha.inventory_service.model.Inventory;
import com.binitshrestha.inventory_service.repository.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadData(InventoryRepository inventoryRepository){
		return args -> {
			Inventory inventory = Inventory.builder()
					.skuCode("Samsung Galaxy s25")
					.quantity(100)
					.build();

			Inventory inventory1 = Inventory.builder()
					.skuCode("Samsung Galaxy s23")
					.quantity(0)
					.build();
			inventoryRepository.save(inventory);
			inventoryRepository.save(inventory1);
		};
	}

}
