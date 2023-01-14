package co.edu.cun.pany.services;

import co.edu.cun.pany.domain.Product;
import co.edu.cun.pany.domain.ProductCategory;

import java.util.List;

public interface ProductService {
    public List<Product>listAllProduct();
    public Product getProduct(Long id);
    public Product createProduct(Product product);
    public Product updateProduct(Product product);
    public Product deleteProduct( Long id);
    public List<Product> findByCategory(ProductCategory productCategory);
    public  Product updateStock(Long id, Double quantity);

}
