package api.restaurant.exception;

public class SaleNotFoundException extends Exception{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SaleNotFoundException(Long id) {
        super("Sale not found with ID " + id);
    }
}
