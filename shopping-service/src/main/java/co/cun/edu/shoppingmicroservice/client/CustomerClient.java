package co.cun.edu.shoppingmicroservice.client;

import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import co.cun.edu.shoppingmicroservice.model.Customer;



@FeignClient(name = "customer-service",path = "/customer-resource")
public interface CustomerClient {

	@GetMapping(value = "/customers/{id}")
	public ResponseEntity<Customer>getCustomer(@PathVariable(value="id")Long id);
	
	
}
