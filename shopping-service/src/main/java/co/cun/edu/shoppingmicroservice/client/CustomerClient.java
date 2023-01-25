package co.cun.edu.shoppingmicroservice.client;

import co.cun.edu.shoppingmicroservice.model.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@FeignClient(name = "customer-service",path = "/customer-resource")
public interface CustomerClient {

	@GetMapping(value = "/customers/{id}")
	public ResponseEntity<Customer>getCustomer(@PathVariable(value="id")Long id);
	
	
}
