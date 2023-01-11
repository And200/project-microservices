package co.cun.edu.customermicroservice.service;

import java.util.List;

import co.cun.edu.customermicroservice.domain.Customer;
import co.cun.edu.customermicroservice.domain.Region;

public interface CustomerService {

	public List<Customer>findAllCustomers();
	public List<Customer>findCustomersByRegion(Region region);
	
	public Customer createCustomer(Customer customer);
	public Customer updateCosCustomer(Customer customer);
	public Customer deleteCustomer(Customer customer);
	
	public Customer getCustomer(Long id);
	
}
