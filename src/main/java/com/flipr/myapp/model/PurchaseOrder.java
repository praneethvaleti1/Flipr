package com.flipr.myapp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseOrder {
	
	@Id
	@GeneratedValue
	private  int orderId;
	
	private String productName;
	private String quantity;
	private int pricing;
	private int mrp;
	
	@ManyToOne
	@JoinColumn(name="custId")
	private Customer customer;
	
	@OneToOne
	@JoinColumn(name = "shippingId")
	private ShippingDetails shippingDetails;
	

}
