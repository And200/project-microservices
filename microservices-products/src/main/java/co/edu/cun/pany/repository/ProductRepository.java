package co.edu.cun.pany.repository;

import co.edu.cun.pany.domain.Product;
import co.edu.cun.pany.domain.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product>findByProductCategory(ProductCategory productCategory);
}
