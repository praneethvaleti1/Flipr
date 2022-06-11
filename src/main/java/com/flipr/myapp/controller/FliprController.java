package com.flipr.myapp.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.flipr.myapp.dao.CustomerRepo;
import com.flipr.myapp.dao.OrderRepo;
import com.flipr.myapp.dao.ShippingRepo;
import com.flipr.myapp.model.Customer;
import com.flipr.myapp.model.InputOrder;
import com.flipr.myapp.model.PurchaseOrder;
import com.flipr.myapp.model.ShippingDetails;

@RestController
public class FliprController {

	@Autowired
	CustomerRepo custRepo;

	@Autowired
	OrderRepo orderRepo;

	@Autowired
	ShippingRepo shippingRepo;

	@PostMapping("/addCustomer")
	public ResponseEntity<Customer> addCustomer(@Valid @RequestBody Customer cust) {
		return new ResponseEntity<Customer>(custRepo.save(cust), HttpStatus.CREATED);
	}

	@PostMapping("/purchaseOrder")
	public ResponseEntity<PurchaseOrder> purchaseOrder(@Valid @RequestBody InputOrder inputOrder) throws Exception {
		if (inputOrder.getPricing() > inputOrder.getPricing())
			throw new Exception("Price should not be  greater than MRP");
		else {
			Optional<Customer> cust = custRepo.findById(inputOrder.getCustId());
			PurchaseOrder purchaseOrder = new PurchaseOrder(inputOrder.getOrderId(), inputOrder.getProductName(), inputOrder.getQuantity(),
					inputOrder.getPricing(), inputOrder.getMrp(), cust.get());
			return new ResponseEntity<PurchaseOrder>(orderRepo.save(purchaseOrder), HttpStatus.CREATED);
		}
	}

	@GetMapping("/getShippingDetails")
	public ResponseEntity<List<ShippingDetails>> getShippingDetailsList(@RequestParam String city) {
		
		return null;
	}

}
