package uz.pdp.appwarehouse.controller;

import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.entity.Category;
import uz.pdp.appwarehouse.payload.CategoryDTO;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public Result addCategory(@RequestBody CategoryDTO categoryDTO) {
        Result result = categoryService.addCategory(categoryDTO);
        return result;
    }

    @GetMapping
    public List<Category> getCategories() {
        List<Category> categories = categoryService.getCategories();
        return categories;
    }

    @GetMapping("/{id}")
    public Category getById(@PathVariable Integer id) {
        Category category= categoryService.getById(id);
        return category;
    }

    @DeleteMapping("/{id}")
    public Result deleteCategory(@PathVariable Integer id) {
        Result result = categoryService.deleteById(id);
        return result;
    }

    @PutMapping("/{id}")
    public Result editCategory(@PathVariable Integer id, @RequestBody CategoryDTO categoryDTO) {
        Result result = categoryService.editById(id, categoryDTO);
        return result;
    }
}
