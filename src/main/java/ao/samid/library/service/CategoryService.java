package ao.samid.library.service;

import ao.samid.library.dto.request.CategoryCreateRequest;
import ao.samid.library.dto.request.CategoryUpdateRequest;
import ao.samid.library.dto.response.CategoryDetailedResponse;
import ao.samid.library.dto.response.CategoryResponse;
import ao.samid.library.entity.Category;
import ao.samid.library.handler.CustomException;
import ao.samid.library.mapper.CategoryMapper;
import ao.samid.library.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService implements ICategoryService{
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    @Override
    public List<CategoryResponse> getCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(categoryMapper::fromEntityToResponse)
                .toList();
    }

    @Override
    public CategoryResponse getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .map(categoryMapper::fromEntityToResponse)
                .orElseThrow(()->new RuntimeException("Category not found"));
    }

    @Override
    public void saveCategory(CategoryCreateRequest request) {
        // Category check edirik
        if(categoryRepository.existsByName(request.getName())){
            throw  CustomException.builder()
                    .code(404)
                    .message("Kateqoriya tapılmadı")
                    .build();
        }
        Category category = categoryMapper.fromCreateToEntity(request);
        categoryRepository.save(category);
    }
    @Override
    public void updateCategory(CategoryUpdateRequest request) {
       Category category = categoryRepository
               .findById(request.getId())
               .orElseThrow(()->CustomException
                       .builder()
                       .code(404)
                       .message("Kateqoriya tapılmadı")
                       .build());
       categoryMapper.fromUpdateToEntity(request, category);
       categoryRepository.save(category);
    }
    @Override
    public CategoryDetailedResponse getDetailedCategory(Long id) {
        return categoryRepository.findById(id)
                .map(categoryMapper::fromEntityToDetailedResponse)
                .orElseThrow(()->CustomException.builder()
                        .code(404)
                        .message("Kateqoriya tapılmadı")
                        .build());
    }
    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}
