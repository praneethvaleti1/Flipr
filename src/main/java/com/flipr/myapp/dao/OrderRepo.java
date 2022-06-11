package com.flipr.myapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flipr.myapp.model.PurchaseOrder;

@Repository
public interface OrderRepo extends JpaRepository<PurchaseOrder, Integer> {
	
	@SuppressWarnings("unchecked")
	public PurchaseOrder save(PurchaseOrder order);

}
