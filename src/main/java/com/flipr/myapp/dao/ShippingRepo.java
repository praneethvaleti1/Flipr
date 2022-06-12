package com.flipr.myapp.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.flipr.myapp.model.ShippingDetails;

@Repository
public interface ShippingRepo extends JpaRepository<ShippingDetails, Integer>{
	
	@SuppressWarnings("unchecked")
	public ShippingDetails save(ShippingDetails shippingDetails);
	
	public Optional<ShippingDetails> findByCustCustId(int custId);
	
	
	@Query(value = "SELECT * FROM CUSTOMER INNER JOIN SHIPPING_DETAILS ON CUSTOMER.CUST_ID=SHIPPING_DETAILS.CUST_ID WHERE SHIPPING_DETAILS.CITY=:city", nativeQuery=true)
	public List<ShippingDetails> getNecessaryList(@Param("city") String city);
	 
	
	

}
