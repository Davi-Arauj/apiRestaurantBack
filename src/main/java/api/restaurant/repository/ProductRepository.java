package api.restaurant.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import api.restaurant.entity.Category;
import api.restaurant.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	@Transactional(readOnly = true)
	//@Query("SELECT DISTINCT obj FROM Produto obj INNER JOIN obj.categorias cat WHERE obj.nome LIKE %:nome% AND cat IN :categorias")
	@Query("SELECT DISTINCT obj FROM Product obj INNER JOIN obj.categories cat WHERE obj.nome LIKE %:nome% AND cat IN :categories")
	Page<Product> searchIdProductAndCategory(@Param("nome") String nome,@Param("categories") List<Category> categories, Pageable pageRequest);

	
	//	Page<Product> findDistinctByDescriptionContainingAndCategoriesIn(@Param("description") String description,
//			@Param("categories") List<Category> categories, Pageable pageRequest);

}
