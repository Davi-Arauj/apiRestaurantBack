package api.restaurant.exception;

public class CategoryNotFoundException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CategoryNotFoundException(Long id) {
        super("Category not found with ID " + id);
    }
}
