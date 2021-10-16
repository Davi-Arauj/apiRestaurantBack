package api.restaurant.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import api.restaurant.dto.CategoryDTO;
import api.restaurant.dto.response.CategoryResponseDTO;
import api.restaurant.entity.Category;
import api.restaurant.exception.CategoryNotFoundException;
import api.restaurant.mapper.CategoryMaping;
import api.restaurant.repository.CategoryRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CategoryService {

	private CategoryRepository categoryRepository;

	private CategoryMaping categoryMaping;

	// Criando uma nova categoria.
	public CategoryResponseDTO createCategory(CategoryDTO categoryDTO) {
		Category savedCategory = categoryRepository.save(categoryMaping.toModel(categoryDTO));
		return createMessageResponse(savedCategory.getId(), "Created Category with ID ");
	}

	// Metodo criar menssagem de resposta.
	private CategoryResponseDTO createMessageResponse(Long id, String message) {
		return CategoryResponseDTO.builder().message(message + id).build();
	}

	// Buscando todos as categorias e transformando em DTO.
	public List<CategoryDTO> listAll() {
		List<Category> allCategory = categoryRepository.findAll();
		return allCategory.stream().map(categoryMaping::toDTO).collect(Collectors.toList());
	}

	// Buscando todos as categorias de forma paginada
	public Page<Category> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return categoryRepository.findAll(pageRequest);
	}

	// Buscando uma categoria por o ID, mais antes verifica se ele existe.
	public CategoryDTO findById(Long id) throws CategoryNotFoundException {
		Category category = verifyIfExists(id);
		return categoryMaping.toDTO(category);
	}

	// Metodo verificar se uma categoria existe para nos auxiliar no
	// desenvolvimento.
	private Category verifyIfExists(Long id) throws CategoryNotFoundException {
		return categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException(id));
	}

	// Atualizando uma categoria, primeiro verifica se existe e assim atualiza, se
	// não existir ele será criado.
	public CategoryResponseDTO updateById(Long id, CategoryDTO categoryDTO) throws CategoryNotFoundException {
		verifyIfExists(id);
		Category categoryToUpdate = categoryMaping.toModel(categoryDTO);
		Category updatedCategory = categoryRepository.save(categoryToUpdate);
		return createMessageResponse(updatedCategory.getId(), "Updated Category with ID ");
	}

	// Deletando uma categoria por o ID, mais antes verifica se ele existe.
	public void delete(Long id) throws CategoryNotFoundException {
		verifyIfExists(id);
		categoryRepository.deleteById(id);
	}

}
