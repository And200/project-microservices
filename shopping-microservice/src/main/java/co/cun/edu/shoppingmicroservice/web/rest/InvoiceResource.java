package co.cun.edu.shoppingmicroservice.web.rest;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import co.cun.edu.shoppingmicroservice.domain.Invoice;
import co.cun.edu.shoppingmicroservice.service.InvoiceService;
import co.cun.edu.shoppingmicroservice.web.rest.errors.ErrorMessage;
import lombok.extern.slf4j.Slf4j;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/invoice-resource")
public class InvoiceResource {

    
   private final InvoiceService invoiceService;
   

   public InvoiceResource(InvoiceService invoiceService) {
	   this.invoiceService=invoiceService;
   }
   
  
    @GetMapping(value = "/invoices")
    public ResponseEntity<List<Invoice>> listAllInvoices() {
        List<Invoice> invoices = invoiceService.findInvoiceAll();
        if (invoices.isEmpty()) {
            return  ResponseEntity.noContent().build();
        }
        return  ResponseEntity.ok(invoices);
    }

    
    @GetMapping(value = "/invoices/{id}")
    public ResponseEntity<Invoice> getInvoice(@PathVariable("id") long id) {
        log.info("Fetching Invoice with id {}", id);
        Invoice invoice  = invoiceService.getInvoice(id);
        if (null == invoice) {
            log.error("Invoice with id {} not found.", id);
            return  ResponseEntity.notFound().build();
        }
        return  ResponseEntity.ok(invoice);
    }

    @PostMapping(value = "/invoices")
    public ResponseEntity<Invoice> createInvoice(@Valid @RequestBody Invoice invoice, BindingResult result) throws URISyntaxException {
        log.info("Creating Invoice : {}", invoice);
        if (result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(result));
        }
        Invoice invoiceDB = invoiceService.createInvoice (invoice);

        return  ResponseEntity.created(new URI("/invoices/"+invoiceDB.getId())).body(invoiceDB);
    }


    @PutMapping(value = "/invoices/{id}")
    public ResponseEntity<?> updateInvoice(@PathVariable("id") long id, @RequestBody Invoice invoice) {
        log.info("Updating Invoice with id {}", id);

        invoice.setId(id);
        Invoice currentInvoice=invoiceService.updateInvoice(invoice);

        if (currentInvoice == null) {
            log.error("Unable to update. Invoice with id {} not found.", id);
            return  ResponseEntity.notFound().build();
        }
        return  ResponseEntity.ok(currentInvoice);
    }

    @DeleteMapping(value = "/invoices/{id}")
    public ResponseEntity<Invoice> deleteInvoice(@PathVariable("id") long id) {
        log.info("Fetching & Deleting Invoice with id {}", id);

        Invoice invoice = invoiceService.getInvoice(id);
        if (invoice == null) {
            log.error("Unable to delete. Invoice with id {} not found.", id);
            return  ResponseEntity.notFound().build();
        }
        invoice = invoiceService.deleteInvoice(invoice);
        return ResponseEntity.ok(invoice);
    }

    private String formatMessage( BindingResult result){
        List<Map<String,String>> errors = result.getFieldErrors().stream()
                .map(err ->{
                    Map<String,String> error =  new HashMap<>();
                    error.put(err.getField(), err.getDefaultMessage());
                    return error;

                }).collect(Collectors.toList());
        ErrorMessage errorMessage = ErrorMessage.builder()
                .code("01")
                .messages(errors).build();
        ObjectMapper mapper = new ObjectMapper();
        String jsonString="";
        try {
            jsonString = mapper.writeValueAsString(errorMessage);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonString;
    }
}