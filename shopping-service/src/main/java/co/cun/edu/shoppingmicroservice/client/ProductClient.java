package co.cun.edu.shoppingmicroservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import co.cun.edu.shoppingmicroservice.model.Product;


@FeignClient(value = "product-service",path = "/products-resource")
public interface ProductClient {
	
	
	@GetMapping(value = "/products/{id}")
	public ResponseEntity<Product>getProduct(@PathVariable(value="id") Long id);
	
	 @GetMapping(value = "/products/{id}/stock")
 public ResponseEntity<Product>updateStock(@PathVariable(value = "id" ) Long id,
		 @RequestParam(name = "quantity",required = true) Double quantity);
	 

	
	

}
