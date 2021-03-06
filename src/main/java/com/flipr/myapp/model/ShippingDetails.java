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
public class ShippingDetails {
	
	@Id
	@GeneratedValue
	private int shippingId;
	private String adress;
	private String city;
	private String pinCode;
	
	@ManyToOne
	@JoinColumn(name = "custId")
	private Customer cust;
	
	

}
