package com.flipr.myapp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.flipr.myapp.model.PurchaseOrder;

@Repository
public interface OrderRepo extends JpaRepository<PurchaseOrder, Integer> {
	
	@SuppressWarnings("unchecked")
	public PurchaseOrder save(PurchaseOrder order);
	
	@Query(value = "SELECT * FROM CUSTOMER  INNER JOIN PURCHASE_ORDER ON CUSTOMER.CUST_ID=PURCHASE_ORDER.CUST_ID", nativeQuery = true)
	public List<PurchaseOrder> getCustWithOrderList();
	
	@Query(value = "SELECT * FROM PURCHASE_ORDER INNER JOIN SHIPPING_DETAILS ON PURCHASE_ORDER.SHIPPING_ID=SHIPPING_DETAILS.SHIPPING_ID", nativeQuery = true)
	public List<PurchaseOrder> getCustWithOrderWithShippingList();

}
