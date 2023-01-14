package co.edu.cun.pany.services;


import co.edu.cun.pany.domain.Product;
import co.edu.cun.pany.domain.ProductCategory;
import co.edu.cun.pany.repository.ProductRepository;
import co.edu.cun.pany.services.imp.ProductServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Optional;

@SpringBootTest
public class ProductServiceMockTest {

    @Mock
    private ProductRepository productRepository;
    private ProductService productService;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        productService = ProductServiceImpl.builder()
                .productRepository(productRepository)
                .build();

        Product pan= Product.builder().
                id(1L).
                name("Pan Hogaza").
                productCategory(ProductCategory.builder().id(1L).build())
                .stock(22.0)
                .price(200.0)
                .status("CREATED")
                .build();
        Mockito.when(this.productRepository.findById(pan.getId()))
                .thenReturn(Optional.of(pan));
        Mockito.when(this.productRepository.save(pan)).thenReturn(pan);

    }

    @Test
    public void findProduct(){

        Product productFind=this.productService.getProduct(1L);
        Assertions.assertThat(productFind.getName()).isEqualTo("Pan Hogaza");


    }

    @Test
    public void newStock(){
        Product newStock= this.productService.updateStock(1L,200.0);
        Assertions.assertThat(newStock.getStock()).isEqualTo(222.0);

    }
}
