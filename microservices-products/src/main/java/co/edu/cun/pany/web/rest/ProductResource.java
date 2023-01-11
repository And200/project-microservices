package co.edu.cun.pany.web.rest;


import co.edu.cun.pany.domain.Product;
import co.edu.cun.pany.domain.ProductCategory;
import co.edu.cun.pany.services.ProductService;
import co.edu.cun.pany.services.errors.ErrorMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping( "/products-resource")
public class ProductResource {

    private final ProductService productService;
        public ProductResource(ProductService productService){
            this.productService=productService;
        }


        @GetMapping(value = "/products")
        public ResponseEntity<List<Product>>getAllProducts(){

            List<Product>productList=this.productService.listAllProduct();
            if(productList ==null){
                return ResponseEntity.noContent().build();
            }
            return  ResponseEntity.ok().body(productList);
        }


        /*
         ** Filter With Parameter

        @GetMapping(value = "/products/category/{id}")
    public ResponseEntity<List<Product>>getProductsByCategory( @PathVariable Long id){
            List<Product>productList;
            if(id==null){
               productList= this.productService.listAllProduct();
                if(productList==null){
                    return ResponseEntity.noContent().build();
                }
            }else{
              productList=  this.productService.findByCategory(ProductCategory.builder().id(id).build());
                    if(productList.isEmpty()){
                        ResponseEntity.notFound().build();
                    }
            }

          return ResponseEntity.ok().body(productList);

        }
         */

    @GetMapping(value = "/products/category")
    public ResponseEntity<List<Product>>getProductsByCategory(@RequestParam(name = "id",required = false)  Long id){
        List<Product>productList;
        if(id==null){
            productList= this.productService.listAllProduct();
            if(productList==null){
                return ResponseEntity.noContent().build();
            }
        }else{
            productList=  this.productService.findByCategory(ProductCategory.builder().id(id).build());
            if(productList.isEmpty()){
                ResponseEntity.notFound().build();
            }
        }

        return ResponseEntity.ok().body(productList);

    }

    @GetMapping(value = "/products/{id}")
    public ResponseEntity<Product>getProductById(@PathVariable Long id){
        Product product=this.productService.getProduct(id);
        if(product==null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().body(product);
    }

    @PostMapping(value = "/products")
    ResponseEntity<Product>createProduct(@Valid @RequestBody Product product, BindingResult bindingResult) throws URISyntaxException {

        if(bindingResult.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(bindingResult));
        }

        if(product.getId()!=null){
            return ResponseEntity.badRequest().build();
        }
        Product result=this.productService.createProduct(product);

        return ResponseEntity.created(new URI("/api/products/"+result.getId())).body(result);
    }


    @PutMapping(value = "/products/{id}")
    ResponseEntity<Product>updateProduct(@PathVariable Long id,@RequestBody Product product){
        product.setId(id);
       Product productQuery=this.productService.updateProduct(product);
        if(productQuery==null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().body(productQuery);
    }
    @GetMapping(value = "/products/{id}/stock")
    ResponseEntity<Product>updateStock(@PathVariable Long id,@RequestParam(name = "quantity",required = true) Double quantity){

        Product product=this.productService.updateStock(id,quantity);
        return ResponseEntity.ok().body(product);
    }

    @DeleteMapping(value = "/products/{id}")
    public ResponseEntity<Void>deleteProduct(@PathVariable Long id){
            this.productService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }

    private String formatMessage(BindingResult result){
        List<Map<String ,String >>errors=result.getFieldErrors().
                stream()
                .map(e->{
                        Map<String,String>error=new HashMap<>();
                        error.put(e.getField(),e.getDefaultMessage());
                        return error;
                }).toList();
        ErrorMessage errorMessage=ErrorMessage.builder()
                .code("01").messagesList(errors).build();

        ObjectMapper mapper=new ObjectMapper();
        String jsonMessages="";
        try{
            jsonMessages=mapper.writeValueAsString(errorMessage);

        }catch (JsonProcessingException e){
            e.printStackTrace();
        }
        return jsonMessages;

    }

}
