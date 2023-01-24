package co.cun.edu.shoppingmicroservice.service.impl;

import java.util.List;

import java.util.Set;
import java.util.stream.Collectors;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.cun.edu.shoppingmicroservice.client.CustomerClient;
import co.cun.edu.shoppingmicroservice.client.ProductClient;
import co.cun.edu.shoppingmicroservice.domain.Invoice;
import co.cun.edu.shoppingmicroservice.domain.InvoiceItem;
import co.cun.edu.shoppingmicroservice.model.Customer;
import co.cun.edu.shoppingmicroservice.model.Product;
import co.cun.edu.shoppingmicroservice.repository.InvoiceRepository;
import co.cun.edu.shoppingmicroservice.service.InvoiceService;

@Service
@Slf4j
public class InvoiceServiceImpl implements InvoiceService {
	

	@Autowired
	 InvoiceRepository invoiceRepository;


	@Autowired
	 CustomerClient customerClient;


	@Autowired
	 ProductClient productClient;

	 public InvoiceServiceImpl(InvoiceRepository invoiceRepository,CustomerClient customerClient,ProductClient productClient) {
		 this.invoiceRepository=invoiceRepository;
		 this.customerClient=customerClient;
		 this.productClient=productClient;

	}



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
		
		invoiceQr=invoiceRepository.save(invoice);
		
		invoiceQr.getItems().forEach(
				items->{
					this.productClient.updateStock(items.getId(),items.getQuantity()*-1);
				}
				);
		return invoiceQr;
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


        Invoice invoice= this.invoiceRepository.findById(id).orElse(null);
        
        if(invoice!=null) {
        	Customer customer=this.customerClient.getCustomer(invoice.getCustomerId()).getBody();
        	invoice.setCustomer(customer);
        	Set<InvoiceItem>listItems=invoice.getItems().stream().map(
        			item->{
        				Product product=this.productClient.getProduct(item.getProductId()).getBody();
        				
        				item.setProduct(product);
        				return item;
        			}
        			).collect(Collectors.toSet());
      
        		invoice.setItems(listItems);
        }
 
        return invoice;
    }




}
