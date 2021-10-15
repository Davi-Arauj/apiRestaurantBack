package api.restaurant.exception;

public class ProductNotFoundException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ProductNotFoundException(Long id) {
        super("Product not found with ID " + id);
    }
}
