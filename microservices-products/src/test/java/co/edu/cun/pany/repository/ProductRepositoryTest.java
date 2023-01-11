package co.edu.cun.pany.repository;
import co.edu.cun.pany.domain.Product;
import co.edu.cun.pany.domain.ProductCategory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;
import java.util.List;

@DataJpaTest
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void findByCategory(){
        Product product01 = Product.builder()
                .name("Pan De Bono")
                .productCategory(ProductCategory.builder().id(1L).build())
                .description("")
                .stock(100.0)
                .price(200.0)
                .status("CREATED")
                .createDate(new Date()).build();
        productRepository.save(product01);

        List<Product> founds= productRepository.findByProductCategory(product01.getProductCategory());

        Assertions.assertThat(founds.size()).isEqualTo(3);


    }
}