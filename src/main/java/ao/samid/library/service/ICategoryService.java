package ao.samid.library.service;

import ao.samid.library.dto.request.CategoryCreateRequest;
import ao.samid.library.dto.request.CategoryUpdateRequest;
import ao.samid.library.dto.response.CategoryDetailedResponse;
import ao.samid.library.dto.response.CategoryResponse;
import ao.samid.library.entity.Category;

import java.util.List;

public interface ICategoryService {
    public List<CategoryResponse> getCategories();
    public CategoryResponse getCategoryById(Long id);
    void saveCategory(CategoryCreateRequest category);
    void updateCategory(CategoryUpdateRequest category);
    void deleteCategory(Long id);
    CategoryDetailedResponse getDetailedCategory(Long id);
}
