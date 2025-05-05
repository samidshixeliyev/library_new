package ao.samid.library.controller;

import ao.samid.library.dto.request.CategoryCreateRequest;
import ao.samid.library.dto.request.CategoryUpdateRequest;
import ao.samid.library.dto.response.BaseResponse;
import ao.samid.library.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/category")
public class CategoryController {
    private final CategoryService categoryService;
    @PostMapping()
    public ResponseEntity<?> createCategory(@RequestBody CategoryCreateRequest request) {
        categoryService.saveCategory(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @PutMapping()
    public ResponseEntity<?> updateCategory(@RequestBody CategoryUpdateRequest request) {
        categoryService.updateCategory(request);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/all")
    public ResponseEntity<?> getAllCategory() {
        return ResponseEntity.ok(BaseResponse.builder()
                .uuid(UUID.randomUUID().toString())
                .data(categoryService.getCategories())
                .build());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable Long id) {
       return ResponseEntity.ok(BaseResponse.builder().uuid(UUID.randomUUID().toString())
                .data(categoryService.getCategoryById(id))
                .status(HttpStatus.OK.value())
                .build());
    }
    @GetMapping("detail/{id}")
    public ResponseEntity<?> getCategoryDetail(@PathVariable Long id) {
        return ResponseEntity.ok(BaseResponse.builder()
                .uuid(UUID.randomUUID().toString())
                .data(categoryService.getDetailedCategory(id))
                .status(HttpStatus.OK.value())
                .build());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }


}
