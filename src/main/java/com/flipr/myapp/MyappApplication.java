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
		
		Customer c1 = new Customer(1, "Customer1", "cust1@gmail.com", "1234567890", "City1");
		Customer c2 = new Customer(2, "Customer2", "cust2@gmail.com", "1234567891", "City2");
		Customer c3 = new Customer(3, "Customer3", "cust3@gmail.com", "1234567892", "City3");
		
		
		customerRepo.save(c1);
		customerRepo.save(c2);
		customerRepo.save(c3);
		
		ShippingDetails sd1 = new ShippingDetails(4, "address1", "city1", "pincode1", c1);
		ShippingDetails sd2 = new ShippingDetails(5, "address2", "city2", "pincode1", c2);
		ShippingDetails sd3 = new ShippingDetails(6, "address3", "city3", "pincode1", c3);
		
		shippingRepo.save(sd1);
		shippingRepo.save(sd2);
		shippingRepo.save(sd3);
		
		PurchaseOrder pr1 = new PurchaseOrder(7, "product1", "quantity1", 121, 121, c1, sd1);
		PurchaseOrder pr2 = new PurchaseOrder(8, "product2", "quantity2", 122, 122, c2, sd2);
		PurchaseOrder pr3 = new PurchaseOrder(9, "product3", "quantity3", 123, 123, c3, sd3);

		orderRepo.save(pr1);
		orderRepo.save(pr2);
		orderRepo.save(pr3);
		 
		
	}
	
}
