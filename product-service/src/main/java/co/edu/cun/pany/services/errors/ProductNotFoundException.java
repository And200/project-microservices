package co.edu.cun.pany.services.errors;

public class ProductNotFoundException extends RuntimeException{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

public  ProductNotFoundException(Long id){
    super("Product Not Found"+id);
}
}
