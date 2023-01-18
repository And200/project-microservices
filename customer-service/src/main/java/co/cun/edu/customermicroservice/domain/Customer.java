package co.cun.edu.customermicroservice.domain;

import java.io.Serializable;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@Entity
@Table(name="customer")
public class Customer implements Serializable{

	private static final long serialVersionUID=	1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY )
	private Long id;
	
	@Size(min = 10, max=10, message= "The character not could higher and less than 11 characters")
	@Column(name="document_number",unique=true,length=11,nullable=false )
	private String documentNumber;
	
	@NotEmpty(message="the first name must not be Null")
	@Column(name="first_name",nullable=false)
	private String firstName;
	
	@NotEmpty(message="the last name must not be null")
	@Column(name="last_name", nullable=false)
	private String lastName;
	
	
   
	@NotEmpty(message =  "the email must not be null")
	@Email(message = "this not have the correct format for email")
	@Column(name = "email",unique = true,nullable = false)
	private String email;
	
	
	@Column(name = "photo")
	private String photoUrl;
	
	
	@NotNull(message = "the region could'nt be null")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "region_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Region region;
	
	@Column(name = "state",length = 20)
	private String state;
	
	
	
	
	
	
	
}
