package com.flipr.myapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OutputShippingDetails {
	
	private String adress;
	private String city;
	private String pinCode;

}
