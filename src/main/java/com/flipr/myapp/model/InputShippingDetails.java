package com.flipr.myapp.model;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InputShippingDetails {
	
	private int shippingId;
	private String adress;
	private String city;
	private String pinCode;
	@NotNull
	private int custId;
	
	

}
