package co.cun.edu.customermicroservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.cun.edu.customermicroservice.domain.Customer;
import co.cun.edu.customermicroservice.domain.Region;

public interface CustomerRepository extends JpaRepository<Customer ,Long> {

	public Customer findByDocumentNumber(String number);
	public List<Customer>findByLastName(String lastName);
	public List<Customer>findByRegion(Region region);
	
	
}
