package com.flipr.myapp;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.flipr.myapp.dao.CustomerRepo;
import com.flipr.myapp.dao.OrderRepo;
import com.flipr.myapp.dao.ShippingRepo;
import com.flipr.myapp.model.Customer;
import com.flipr.myapp.model.PurchaseOrder;
import com.flipr.myapp.model.ShippingDetails;

@SpringBootApplication
public class MyappApplication {
	
	@Autowired
	CustomerRepo customerRepo;
	
	@Autowired
	OrderRepo orderRepo;
	
	@Autowired
	ShippingRepo shippingRepo;
	

	public static void main(String[] args) {
		SpringApplication.run(MyappApplication.class, args);
	}
	
	@PostConstruct
	public void init() {
		
		customerRepo.save(new Customer(1,"Customer1","cust1@gmail.com","1234567890", "City1"));
		customerRepo.save(new Customer(2,"Customer2","cust2@gmail.com","1234567891", "City2"));
		customerRepo.save(new Customer(3,"Customer3","cust3@gmail.com","1234567892", "City3"));
		
		orderRepo.save(new PurchaseOrder(1, "product1", "1", 121, 121, new Customer(1,"Customer1","cust1@gmail.com","1234567890", "City1")));
		orderRepo.save(new PurchaseOrder(2, "product2", "2", 122, 122, new Customer(2,"Customer2","cust2@gmail.com","1234567891", "City2")));
		orderRepo.save(new PurchaseOrder(3, "product3", "3", 123, 123, new Customer(3,"Customer3","cust3@gmail.com","1234567892", "City3")));
		
		shippingRepo.save(new ShippingDetails(1, "Jain University", "Bangalore", "12345", new PurchaseOrder(4, "product1", "1", 121, 121), new Customer(1,"Customer1","cust1@gmail.com","1234567890", "City1")));
		
	}
	
}
