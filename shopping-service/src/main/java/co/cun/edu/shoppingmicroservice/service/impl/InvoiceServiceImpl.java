package co.cun.edu.shoppingmicroservice.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.cun.edu.shoppingmicroservice.domain.Invoice;
import co.cun.edu.shoppingmicroservice.repository.InvoiceRepository;
import co.cun.edu.shoppingmicroservice.service.InvoiceService;

@Service
public class InvoiceServiceImpl implements InvoiceService {
	
	@Autowired
	InvoiceRepository invoiceRepository;

	@Override
	public List<Invoice> findInvoiceAll() {

		return this.invoiceRepository.findAll();
	}

	@Override
	public Invoice createInvoice(Invoice invoice) {
		Invoice invoiceQr=this.invoiceRepository
				.findByInvoiceNumber(invoice.getInvoiceNumber());
		if(invoiceQr!=null) {
			return invoiceQr;
		}
		invoice.setState("CREATED");
		return this.invoiceRepository.save(invoice);
	}

	@Override
	public Invoice updateInvoice(Invoice invoice) {
		Invoice invoiceQr=getInvoice(invoice.getId());
		if(invoiceQr==null) {
			return null;
		}
		invoiceQr.setCustomerId(invoice.getCustomerId());
		invoiceQr.setDescription(invoice.getDescription());
		invoiceQr.setInvoiceNumber(invoice.getInvoiceNumber());
		invoiceQr.getItems().clear();
		invoiceQr.setItems(invoice.getItems());
		
		
		return this.invoiceRepository.save(invoiceQr);
	}

	@Override
	public Invoice deleteInvoice(Invoice invoice) {
		Invoice invoiceQr=getInvoice(invoice.getId());
		if(invoiceQr==null) {
			return null;
		}
		invoiceQr.setState("DELETED");
		return this.invoiceRepository.save(invoiceQr);
	}
	
	
    @Override
    public Invoice getInvoice(Long id) {
        return this.invoiceRepository.findById(id).orElse(null);
    }
	
	
	

}
