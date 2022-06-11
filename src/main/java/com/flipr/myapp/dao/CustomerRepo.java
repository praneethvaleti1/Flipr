package com.flipr.myapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flipr.myapp.model.Customer;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Integer> {

	//public List<Customer> findCustomers();
	
	@SuppressWarnings("unchecked")
	public Customer save(Customer cust);

}
