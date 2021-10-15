package api.restaurant.mapper;

import org.springframework.stereotype.Component;

import api.restaurant.dto.CategoryDTO;
import api.restaurant.entity.Category;

@Component
public class CategoryMaping implements CategoryMap{

	@Override
	public Category toModel(CategoryDTO categoryDTO) {
		// TODO Auto-generated method stub
		Category cat = new Category();
		cat.setId(categoryDTO.getId());
		cat.setName(categoryDTO.getName());
		return cat;
	}

	@Override
	public CategoryDTO toDTO(Category category) {
		// TODO Auto-generated method stub
		CategoryDTO catDto = new CategoryDTO();
		catDto.setId(category.getId());
		catDto.setName(category.getName());
		return catDto;
	}

}
