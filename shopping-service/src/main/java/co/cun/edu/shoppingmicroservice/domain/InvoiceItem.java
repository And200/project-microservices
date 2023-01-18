package co.cun.edu.shoppingmicroservice.domain;

import java.io.Serializable;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import co.cun.edu.shoppingmicroservice.model.Product;
import lombok.Data;

@Data
@Entity
@Table(name = "invoice_item")
public class InvoiceItem implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Positive(message = "the stock should be highter than 0")
	@Column(name = "quantity")
	private Double quantity;
	@Column(name = "price")
	private Double price;
	
	@Column(name = "product_id")
	private Long productId;
	
	@Transient
	private Double subTotal;
	
	 @Transient
	 private Product product;
	 
	
	 @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	    @ManyToOne(optional = false,fetch = FetchType.LAZY)
	    @JoinColumn(name = "invoiceitem_id",referencedColumnName = "id",
	    foreignKey = @ForeignKey(name = "fk_invoice_item"))
	   @Valid
	   @NotNull(message = "The Invoice must not Be Null")
	    private Invoice invoice;
	 
	
	public Double getSubTotal() {
		if(this.price>0 && this.quantity>0) {
		return	this.subTotal=price*quantity;
		}else {
			return (double)0;
		}
	}

	
	public InvoiceItem() {
		this.quantity=(double)0;
		this.price=(double)0;
	}
}
