package co.cun.edu.shoppingmicroservice.client;

import co.cun.edu.shoppingmicroservice.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(value = "product-service",path = "/products-resource")
public interface ProductClient {
	
	
	@GetMapping(value = "/products/{id}")
	public ResponseEntity<Product>getProduct(@PathVariable(value="id") Long id);
	
	 @GetMapping(value = "/products/{id}/stock")
 public ResponseEntity<Product>updateStock(@PathVariable(value = "id" ) Long id,
		 @RequestParam(name = "quantity",required = true) Double quantity);
	 

	
	

}
