package com.flipr.myapp.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustWithOrderWithShipment {
	
	private int custId;
	private List<OutputOrderWithShippingDetails> outputOrderWithShippingDetailsList;

}
