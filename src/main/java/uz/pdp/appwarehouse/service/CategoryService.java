package uz.pdp.appwarehouse.service;

import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.entity.Category;
import uz.pdp.appwarehouse.payload.CategoryDTO;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    public Result addCategory(CategoryDTO categoryDTO) {

        boolean existsByNameAndParentCategoryId = categoryRepository.existsByNameAndParentCategoryId(categoryDTO.getName(), categoryDTO.getParentCategoryId());
        if (existsByNameAndParentCategoryId) {
            return new Result("Such parent category and child category already exists", false);
        }

        Category category = new Category();
        category.setName(categoryDTO.getName());

        if (categoryDTO.getParentCategoryId() != null) {

            Optional<Category> optionalCategory = categoryRepository.findById(categoryDTO.getParentCategoryId());

            if (!optionalCategory.isPresent())

                return new Result("Such parent category not found", false);

            category.setParentCategory(optionalCategory.get());
        }
        categoryRepository.save(category);
        return new Result("Category added", true);

    }


    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    public Category getById(Integer id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        return optionalCategory.orElse(null);

    }

    public Result deleteById(Integer id) {

        boolean exists = categoryRepository.existsById(id);
        if (!exists)
            return new Result("Such category not found", false);
        categoryRepository.deleteById(id);
        return new Result("Category deleted", true);
    }

    public Result editById(Integer id, CategoryDTO categoryDTO) {

        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isPresent()) {
            Category editing = optionalCategory.get();
            editing.setName(categoryDTO.getName());
            editing.setActive(categoryDTO.getActive());

            Optional<Category> optionalParentCategory = categoryRepository.findById(categoryDTO.getParentCategoryId());
            if (optionalParentCategory.isPresent()) {
                Category parentCategory = optionalParentCategory.get();
                editing.setParentCategory(parentCategory);
                categoryRepository.save(editing);
                return new Result("Category edited", true);

            } else {
                return new Result("Such parent category not found", false);
            }
        }
        return new Result("Such category not found", false);
    }
}