package co.edu.cun.pany.repository;

import co.edu.cun.pany.domain.Product;
import co.edu.cun.pany.domain.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory,Long> {

}