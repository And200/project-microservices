package co.cun.edu.shoppingmicroservice.service;

import co.cun.edu.shoppingmicroservice.domain.Invoice;

import java.util.List;

public interface InvoiceService {

	public List<Invoice>findInvoiceAll();
		
	public Invoice createInvoice(Invoice invoice);
	public Invoice updateInvoice(Invoice invoice);
	public Invoice deleteInvoice(Invoice invoice);
	public Invoice getInvoice(Long id);
	
}
