package com.flipr.myapp.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
import com.flipr.myapp.model.CustWithOrderList;
import com.flipr.myapp.model.CustWithOrderWithShipment;
import com.flipr.myapp.model.Customer;
import com.flipr.myapp.model.InputOrder;
import com.flipr.myapp.model.InputShippingDetails;
import com.flipr.myapp.model.OutputOrder;
import com.flipr.myapp.model.OutputOrderWithShippingDetails;
import com.flipr.myapp.model.OutputShippingDetails;
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

	@PostMapping("/addShippingDetails")
	public ResponseEntity<ShippingDetails> addShippingDetails(
			@Valid @RequestBody InputShippingDetails inputShippingDetails) {
		Optional<Customer> cust = custRepo.findById(inputShippingDetails.getCustId());
		ShippingDetails shippingDetails = new ShippingDetails(inputShippingDetails.getShippingId(),
				inputShippingDetails.getAdress(), inputShippingDetails.getCity(), inputShippingDetails.getPinCode(),
				cust.get());
		return new ResponseEntity<ShippingDetails>(shippingRepo.save(shippingDetails), HttpStatus.CREATED);
	}

	@PostMapping("/purchaseOrder")
	public ResponseEntity<PurchaseOrder> purchaseOrder(@Valid @RequestBody InputOrder inputOrder) throws Exception {
		if (inputOrder.getPricing() > inputOrder.getPricing())
			throw new Exception("Price should not be  greater than MRP");
		else {
			Optional<Customer> cust = custRepo.findById(inputOrder.getCustId());
			Optional<ShippingDetails> shippingDetails = shippingRepo.findByCustCustId(cust.get().getCustId());
			if(shippingDetails.get()==null)  {
				shippingDetails = shippingRepo.findById(inputOrder.getShippingId());
			}
			PurchaseOrder purchaseOrder = new PurchaseOrder(inputOrder.getOrderId(), inputOrder.getProductName(),
					inputOrder.getQuantity(), inputOrder.getPricing(), inputOrder.getMrp(), cust.get(), shippingDetails.get());
			return new ResponseEntity<PurchaseOrder>(orderRepo.save(purchaseOrder), HttpStatus.CREATED);
		}
			
	}

	@GetMapping("/getShippingDetails")
	public ResponseEntity<List<ShippingDetails>> getShippingDetailsList(@RequestParam String city) {
		return new ResponseEntity<List<ShippingDetails>>(shippingRepo.getNecessaryList(city), HttpStatus.OK);
	}
	
	@GetMapping("/customer/purchaseOrder")
	public ResponseEntity<List<CustWithOrderList>> getCustPurchaseOrderList() {
		List<PurchaseOrder> purchaseOrderList = orderRepo.getCustWithOrderList();
		
		List<CustWithOrderList> myList = new ArrayList<>();
		HashSet<Integer> myHash = new HashSet<Integer>();
		for (PurchaseOrder purchaseOrder : purchaseOrderList) {
			if(myHash.add(purchaseOrder.getCustomer().getCustId())==true) {
				CustWithOrderList custWithOrderList = new CustWithOrderList();
				custWithOrderList.setCustId(purchaseOrder.getCustomer().getCustId());
				List<OutputOrder> outputOrderList = new ArrayList<>();
				OutputOrder outputOrder = new OutputOrder();
				outputOrder.setMrp(purchaseOrder.getMrp());
				outputOrder.setOrderId(purchaseOrder.getOrderId());
				outputOrder.setPricing(purchaseOrder.getPricing());
				outputOrder.setProductName(purchaseOrder.getProductName());
				outputOrder.setQuantity(purchaseOrder.getQuantity());
				outputOrderList.add(outputOrder);
				custWithOrderList.setOutputOrderList(outputOrderList);
				myList.add(custWithOrderList);
			} else {
				OutputOrder outputOrder = new OutputOrder();
				outputOrder.setMrp(purchaseOrder.getMrp());
				outputOrder.setOrderId(purchaseOrder.getOrderId());
				outputOrder.setPricing(purchaseOrder.getPricing());
				outputOrder.setProductName(purchaseOrder.getProductName());
				outputOrder.setQuantity(purchaseOrder.getQuantity());
				myList.stream()
						.collect(Collectors.toMap(CustWithOrderList::getCustId, CustWithOrderList::getOutputOrderList))
						.get(purchaseOrder.getCustomer().getCustId()).add(outputOrder);
			}
			
		}
		return new ResponseEntity<List<CustWithOrderList>>(myList, HttpStatus.OK);
	}
	
	@GetMapping("/customer/purchaseOrder/WithShipmentDetail")
	public ResponseEntity<List<CustWithOrderWithShipment>> getCustPurchaseOrderWithShipmentList() {
		List<PurchaseOrder> purchaseOrderList = orderRepo.getCustWithOrderWithShippingList();
		List<CustWithOrderWithShipment> myList = new ArrayList<CustWithOrderWithShipment>();
		HashSet<Integer> myHash = new HashSet<Integer>();
		for (PurchaseOrder purchaseOrder : purchaseOrderList) {
			if(myHash.add(purchaseOrder.getCustomer().getCustId())==true) {
				CustWithOrderWithShipment custWithOrderWithShipment =  new CustWithOrderWithShipment();
				custWithOrderWithShipment.setCustId(purchaseOrder.getCustomer().getCustId());
				List<OutputOrderWithShippingDetails> outputOrderWithShippingDetailsList = new ArrayList<>();
				OutputOrderWithShippingDetails outputOrderWithShippingDetails = new OutputOrderWithShippingDetails();
				outputOrderWithShippingDetails.setMrp(purchaseOrder.getMrp());
				outputOrderWithShippingDetails.setOrderId(purchaseOrder.getOrderId());
				outputOrderWithShippingDetails.setPricing(purchaseOrder.getPricing());
				outputOrderWithShippingDetails.setProductName(purchaseOrder.getProductName());
				outputOrderWithShippingDetails.setQuantity(purchaseOrder.getQuantity());
				List<OutputShippingDetails> outputShippingDetailsList = new ArrayList<OutputShippingDetails>();
				OutputShippingDetails outputShippingDetails = new OutputShippingDetails();
				outputShippingDetails.setAdress(purchaseOrder.getShippingDetails().getAdress());
				outputShippingDetails.setCity(purchaseOrder.getShippingDetails().getCity());
				outputShippingDetails.setPinCode(purchaseOrder.getShippingDetails().getPinCode());
				outputShippingDetailsList.add(outputShippingDetails);
				outputOrderWithShippingDetails.setOutputShippingDetailsList(outputShippingDetailsList);
				outputOrderWithShippingDetailsList.add(outputOrderWithShippingDetails);
				custWithOrderWithShipment.setOutputOrderWithShippingDetailsList(outputOrderWithShippingDetailsList);
				myList.add(custWithOrderWithShipment);
			} else {
				OutputOrderWithShippingDetails outputOrderWithShippingDetails = new OutputOrderWithShippingDetails();
				outputOrderWithShippingDetails.setMrp(purchaseOrder.getMrp());
				outputOrderWithShippingDetails.setOrderId(purchaseOrder.getOrderId());
				outputOrderWithShippingDetails.setPricing(purchaseOrder.getPricing());
				outputOrderWithShippingDetails.setProductName(purchaseOrder.getProductName());
				outputOrderWithShippingDetails.setQuantity(purchaseOrder.getQuantity());
				myList.stream().collect(Collectors.toMap(CustWithOrderWithShipment::getCustId, CustWithOrderWithShipment::getOutputOrderWithShippingDetailsList))
				.get(purchaseOrder.getCustomer().getCustId()).add(outputOrderWithShippingDetails);
			}
			
		}
		return new ResponseEntity<List<CustWithOrderWithShipment>>(myList, HttpStatus.OK);
	}
	

}
