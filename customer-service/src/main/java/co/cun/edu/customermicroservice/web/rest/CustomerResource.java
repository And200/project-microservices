package co.cun.edu.customermicroservice.web.rest;

import co.cun.edu.customermicroservice.domain.Customer;
import co.cun.edu.customermicroservice.domain.Region;
import co.cun.edu.customermicroservice.service.CustomerService;
import co.cun.edu.customermicroservice.web.rest.errors.ErrorMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping(value = "/customer-resource")
public class CustomerResource {
	
	private final CustomerService customerService;
	
	public CustomerResource(CustomerService customerService) {
		this.customerService=customerService;
	}
	
	@GetMapping(value = "/customers")
	public ResponseEntity<List<Customer>>getAllCustomers(@RequestParam (name = "regionId",required = false)Long regionId ){
		List<Customer>customers=new ArrayList<>();
		if(regionId==null) {
			customers=customerService.findAllCustomers();
			
				if(customers.isEmpty()) {
						return ResponseEntity.noContent().build();
				}
		
		
		}else {
			Region region=new Region();
			region.setId(regionId);
			customers=this.customerService.findCustomersByRegion(region);
		
			if(customers==null) {
				log.error("Customer with Region id {} not found",regionId );
				return ResponseEntity.notFound().build();
			}
		}
		
		return ResponseEntity.ok().body(customers);
	}
	
	@GetMapping(value = "customers/{id}")
	public ResponseEntity<Customer>getCustomer(@PathVariable Long id){
		log.info("Getting client with id {}",id);
		Customer customer=this.customerService.getCustomer(id);
		if(customer==null) {
			log.error("doesn't exist a client with these id {}",id);
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(customer);
	}
	
	@PostMapping(value = "/customers")
	public ResponseEntity<Customer>createCustomer( @Valid @RequestBody Customer customer,BindingResult bindingResult ) throws URISyntaxException{
		log.info("creating a Customer with id {}",customer);
		if(bindingResult.hasErrors()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,this.formatMessage(bindingResult));
		}
		Customer customerQr=this.customerService.createCustomer(customer);
		return ResponseEntity.created(new URI("/customer-resource/customers/"+customerQr.getId())).body(customerQr);
		

	}
	
	@PutMapping(value = "/customers/{id}")
	public ResponseEntity<Customer>updateCustomer(@PathVariable("id") Long id,@RequestBody Customer customer){
		
		log.info("updating client with id {}",id);
		Customer customerQr=this.customerService.getCustomer(id);
		if(customerQr==null) {
			log.error("not there's a product with these id {}",id);
			return ResponseEntity.notFound().build();
		}
		customer.setId(id);
		customerQr=this.customerService.createCustomer(customer);
		return ResponseEntity.ok().body(customerQr);
	}

	
	
	@DeleteMapping("/customers/{id}")
public ResponseEntity<Void>deleteCustomer(@PathVariable Long id){
		log.info("deleting a product with id {}",id);
		Customer customer=this.customerService.getCustomer(id);
		if(customer==null) {
			log.error("not there's a product with these id");
			return ResponseEntity.notFound().build();
		}
		this.customerService.deleteCustomer(customer);
		
		return ResponseEntity.ok().build();
	}
	
	
	 private String formatMessage( BindingResult result){
	        List<Map<String,String>> errors = result.getFieldErrors().stream()
	                .map(err ->{
	                    Map<String,String>  error =  new HashMap<>();
	                    error.put(err.getField(), err.getDefaultMessage());
	                    return error;

	                }).collect(Collectors.toList());
	        ErrorMessage errorMessage = ErrorMessage.builder()
	                .code("01")
	                .messages(errors).build();
	        ObjectMapper mapper = new ObjectMapper();
	        String jsonString="";
	        try {
	            jsonString = mapper.writeValueAsString(errorMessage);
	        } catch (JsonProcessingException e) {
	            e.printStackTrace();
	        }
	        return jsonString;
	    }
}
