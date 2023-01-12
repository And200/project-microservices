package co.cun.edu.shoppingmicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.cun.edu.shoppingmicroservice.domain.InvoiceItem;

public interface InvoiceItemRepository  extends JpaRepository<InvoiceItem,Long>{

}
