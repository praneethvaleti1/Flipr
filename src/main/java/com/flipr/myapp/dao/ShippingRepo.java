package com.flipr.myapp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.flipr.myapp.model.CustWithOrderList;
import com.flipr.myapp.model.ShippingDetails;

@Repository
public interface ShippingRepo extends JpaRepository<ShippingDetails, Integer>{
	
	@SuppressWarnings("unchecked")
	public ShippingDetails save(ShippingDetails shippingDetails);
	
	/*@Query("SELECT CUSTOMER.CUST_ID"
			+ "  FROM PURCHASE_ORDER\r\n"
			+ "INNER JOIN CUSTOMER\r\n"
			+ "ON CUSTOMER.CUST_ID=PURCHASE_ORDER.CUST_ID\r\n"
			+ "INNER JOIN SHIPPING_DETAILS\r\n"
			+ "ON SHIPPING_DETAILS.CUST_ID=CUSTOMER.CUST_ID AND CUSTOMER.CUST_ID=PURCHASE_ORDER.CUST_ID WHERE SHIPPING_DETAILS.CITY=:city\r\n"
			+ "")
	public List<CustWithOrderList> getNecessaryList(String city);*/
	
	

}
