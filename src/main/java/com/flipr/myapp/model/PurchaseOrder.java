package com.flipr.myapp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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

	public PurchaseOrder(int orderId, String productName, String quantity, int pricing, int mrp) {
		super();
		this.orderId = orderId;
		this.productName = productName;
		this.quantity = quantity;
		this.pricing = pricing;
		this.mrp = mrp;
	}
	

}
