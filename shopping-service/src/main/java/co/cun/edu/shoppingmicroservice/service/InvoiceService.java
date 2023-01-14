package co.cun.edu.shoppingmicroservice.service;

import java.util.List;

import co.cun.edu.shoppingmicroservice.domain.Invoice;

public interface InvoiceService {

	public List<Invoice>findInvoiceAll();
		
	public Invoice createInvoice(Invoice invoice);
	public Invoice updateInvoice(Invoice invoice);
	public Invoice deleteInvoice(Invoice invoice);
	public Invoice getInvoice(Long id);
	
}
