package co.cun.edu.customermicroservice.service;

import co.cun.edu.customermicroservice.domain.Customer;
import co.cun.edu.customermicroservice.domain.Region;

import java.util.List;

public interface CustomerService {

	public List<Customer>findAllCustomers();
	public List<Customer>findCustomersByRegion(Region region);
	
	public Customer createCustomer(Customer customer);
	public Customer updateCosCustomer(Customer customer);
	public Customer deleteCustomer(Customer customer);
	
	public Customer getCustomer(Long id);
	
}
