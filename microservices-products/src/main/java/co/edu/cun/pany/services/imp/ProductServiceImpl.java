package co.edu.cun.pany.services.imp;

import co.edu.cun.pany.domain.Product;
import co.edu.cun.pany.domain.ProductCategory;
import co.edu.cun.pany.repository.ProductRepository;
import co.edu.cun.pany.services.ProductService;
import co.edu.cun.pany.services.errors.ProductNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


 @Service @Builder @AllArgsConstructor
public class ProductServiceImpl implements ProductService {


    private ProductRepository productRepository;


    @Override
    public List<Product>    listAllProduct() {
       return this.productRepository.findAll();
    }

    @Override
    public Product getProduct(Long id) {
        return this.productRepository.findById(id).orElse(null)  ;
    }

    @Override
    public Product createProduct(Product product) {

        product.setStatus("CREATED");
        product.setCreateDate(new Date());
        return this.productRepository.save(product);
    }

    @Override
    public Product updateProduct(Product product) {
        Product productUpdated=getProduct(product.getId());
     if(productUpdated==null){
         throw new ProductNotFoundException(product.getId());
     }
     productUpdated.setName(product.getName());
     productUpdated.setProductCategory(product.getProductCategory());
     productUpdated.setPrice(productUpdated.getPrice());
     productUpdated.setDescription(productUpdated.getDescription());
        return this.productRepository.save(productUpdated);
    }

    @Override
    public Product deleteProduct(Long id) {
        Product productUpdated=getProduct(id);
        if(productUpdated==null){
            throw new ProductNotFoundException(id);

        }
        productUpdated.setStatus("DELETED");
        return this.productRepository.save(productUpdated);

    }

    @Override
    public List<Product> findByCategory(ProductCategory productCategory) {
        return this.productRepository.findByProductCategory(productCategory);

    }

    @Override
    public Product updateStock(Long id, Double quantity) {

        Product productUpdated=getProduct(id);
        if(productUpdated==null){
            throw new ProductNotFoundException(id);

        }
        Double stock=productUpdated.getStock()+quantity;
        productUpdated.setStock(stock);
        return this.productRepository.save(productUpdated);
    }


}
