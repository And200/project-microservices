package co.cun.edu.shoppingmicroservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.cun.edu.shoppingmicroservice.domain.Invoice;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
	
	public List<Invoice> findByCustomerId(Long customerId);
	public Invoice  findByInvoiceNumber(String invoiceNumber);
	
	

}
