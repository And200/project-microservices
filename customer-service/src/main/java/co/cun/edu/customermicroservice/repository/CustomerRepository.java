package co.cun.edu.customermicroservice.repository;

import co.cun.edu.customermicroservice.domain.Customer;
import co.cun.edu.customermicroservice.domain.Region;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer ,Long> {

	public Customer findByDocumentNumber(String number);
	public List<Customer>findByLastName(String lastName);
	public List<Customer>findByRegion(Region region);
	
	
}
