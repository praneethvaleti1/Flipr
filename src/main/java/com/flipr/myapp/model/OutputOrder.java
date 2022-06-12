package com.flipr.myapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OutputOrder {
	
	private int orderId;
	private String productName;
	private String quantity;
	private int pricing;
	private int mrp;

}
