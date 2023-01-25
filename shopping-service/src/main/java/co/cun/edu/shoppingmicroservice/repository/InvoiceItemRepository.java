package co.cun.edu.shoppingmicroservice.repository;

import co.cun.edu.shoppingmicroservice.domain.InvoiceItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceItemRepository  extends JpaRepository<InvoiceItem,Long>{

}
