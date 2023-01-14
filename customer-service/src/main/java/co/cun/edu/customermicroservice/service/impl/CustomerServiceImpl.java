package co.cun.edu.customermicroservice.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.cun.edu.customermicroservice.domain.Customer;
import co.cun.edu.customermicroservice.domain.Region;
import co.cun.edu.customermicroservice.repository.CustomerRepository;
import co.cun.edu.customermicroservice.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	CustomerRepository customerRepository;
	
	public List<Customer> findAllCustomers() {

		return this.customerRepository.findAll();
	}

	@Override
	public List<Customer> findCustomersByRegion(Region region) {
	
		
		return this.customerRepository.findByRegion(region);
	}

	@Override
	public Customer createCustomer(Customer customer) {
	
		Customer customerQuery=this.customerRepository
				.findByDocumentNumber(customer.getDocumentNumber());
		if(customerQuery!=null) {
			return customerQuery;	
		}
		customer.setState("CREATED");
		
		customerQuery =this.customerRepository.save(customer);
		
				return customerQuery;				
	}

	@Override
	public Customer updateCosCustomer(Customer customer) {

		Customer customerQuery=getCustomer(customer.getId());
		
		if(customerQuery==null) {
			return null;
		}
		
		customerQuery.setFirstName(customer.getFirstName());

		customerQuery.setLastName(customer.getLastName());
		customerQuery.setEmail(customer.getEmail());
		customerQuery.setDocumentNumber(customer.getDocumentNumber());
		customerQuery.setPhotoUrl(customer.getPhotoUrl());
		customerQuery.setRegion(customer.getRegion());
		
		
		return null;
	}

	@Override
	public Customer deleteCustomer(Customer customer) {
		
		Customer customerQr=getCustomer(customer.getId());
		if(customerQr==null) {
			return null;
		}
		customer.setState("DELETED");
	
		return this.customerRepository.save(customer);
	}

	@Override
	public Customer getCustomer(Long id) {

		return this.customerRepository.findById(id).orElse(null);
	}

	
}
