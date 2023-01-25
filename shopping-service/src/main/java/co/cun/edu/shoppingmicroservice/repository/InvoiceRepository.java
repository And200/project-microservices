package co.cun.edu.shoppingmicroservice.repository;

import co.cun.edu.shoppingmicroservice.domain.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
	
	public List<Invoice> findByCustomerId(Long customerId);
	public Invoice  findByInvoiceNumber(String invoiceNumber);
	
	

}
