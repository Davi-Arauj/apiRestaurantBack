package api.restaurant.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import api.restaurant.dto.CategoryDTO;
import api.restaurant.entity.Category;

@Mapper(componentModel = "spring")
public interface CategoryMap {

  CategoryMap INSTANCE = Mappers.getMapper(CategoryMap.class);

  
    Category toModel(CategoryDTO categoryDTO);
    CategoryDTO toDTO(Category category);
}
