package co.cun.edu.shoppingmicroservice.domain;

import co.cun.edu.shoppingmicroservice.model.Customer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import javax.validation.Valid;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "invoice")
public class Invoice implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private Long id;
	
	@Column(name = "invoice_number")
	private String invoiceNumber;
	
	
	@Column(name = "description")
	private String description;
	
	
	@Column(name = "customer_id")
	private Long customerId;
	
	@Column(name = "create_date")
	@Temporal(TemporalType.DATE)
	private Date createDate;
	
	@Transient
	private Customer customer;

	@Valid
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinColumn(name = "invoice_id")
	private Set<InvoiceItem> items;
	

	private String state;
	
	public Invoice() {
		items=new HashSet<>();
	}
	
	@PrePersist
	public void prePersist() {
		this.createDate=new Date();
	}
	
	

}
