package co.cun.edu.shoppingmicroservice.domain;

import java.io.Serializable;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import co.cun.edu.shoppingmicroservice.model.Customer;
import lombok.Data;

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
	@JsonIgnore
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	@OneToMany(mappedBy ="invoice" ,fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<InvoiceItem>items;
	
	
	@Column(name = "state")
	private String state;
	
	public Invoice() {
		items=new HashSet<>();
	}
	
	@PrePersist
	public void prePersist() {
		this.createDate=new Date();
	}
	
	

}
