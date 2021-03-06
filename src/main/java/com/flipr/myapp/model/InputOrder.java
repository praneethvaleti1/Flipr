package com.flipr.myapp.model;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InputOrder {
	
	private int orderId;
	private String productName;
	private String quantity;
	private int pricing;
	private int mrp;
	@NotNull
	private int custId;
	private int shippingId;
}
