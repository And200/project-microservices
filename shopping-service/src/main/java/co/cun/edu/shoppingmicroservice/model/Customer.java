package co.cun.edu.shoppingmicroservice.model;

import lombok.Data;

@Data
public class Customer {
	
	private Long id;
	

	private String documentNumber;
	
	
	private String firstName;
	
	
	private String lastName;
	private String email;
	private String photoUrl;
	private Region region;
	private String state;
	
	

}
