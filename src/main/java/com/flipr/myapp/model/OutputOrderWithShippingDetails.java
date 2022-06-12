package com.flipr.myapp.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OutputOrderWithShippingDetails {
	
	private int orderId;
	private String productName;
	private String quantity;
	private int pricing;
	private int mrp;
	private List<OutputShippingDetails> outputShippingDetailsList;

}
