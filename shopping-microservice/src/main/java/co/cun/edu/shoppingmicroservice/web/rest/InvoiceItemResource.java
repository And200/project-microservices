package co.cun.edu.shoppingmicroservice.web.rest;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.cun.edu.shoppingmicroservice.domain.InvoiceItem;
import co.cun.edu.shoppingmicroservice.repository.InvoiceItemRepository;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
@RequestMapping("invoice-items")
public class InvoiceItemResource {

	private final InvoiceItemRepository invoiceItemRepository;
	
	public InvoiceItemResource(InvoiceItemRepository invoiceItemRepository) {
		this.invoiceItemRepository=invoiceItemRepository;
	}
	
	@GetMapping(value = "/items")
	ResponseEntity<List<InvoiceItem>>getAllInvoiceItems(){
		
		log.info("getting all Invoice Items");
		List<InvoiceItem>invoiceItems=this.invoiceItemRepository.findAll();
		
		if(invoiceItems==null) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok().body(invoiceItems);
	}
}
