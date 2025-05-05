package ao.samid.library.controller;

import ao.samid.library.dto.request.AuthorCreateRequest;
import ao.samid.library.dto.request.AuthorUpdateRequest;
import ao.samid.library.dto.response.BaseResponse;
import ao.samid.library.entity.Author;
import ao.samid.library.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/author")
public class AuthorController {
    private final AuthorService authorService;
    @PostMapping
    public ResponseEntity<?> save(@RequestBody AuthorCreateRequest request) {
        return new ResponseEntity<>(BaseResponse.builder()
                .uuid(UUID.randomUUID().toString())
                .data(authorService.saveAuthor(request))
                .status(HttpStatus.CREATED.value())
                .build(),HttpStatus.CREATED);
    }
    @PutMapping
    public ResponseEntity<?> update(@RequestBody AuthorUpdateRequest request) {
        authorService.updateAuthor(request);
        return ResponseEntity
                .ok()
                .build();
    }
    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        return ResponseEntity
                .ok(BaseResponse.builder()
                        .uuid(UUID.randomUUID().toString())
                        .data(authorService.getAuthors())
                        .status(HttpStatus.OK.value())
                        .build());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return ResponseEntity.ok(BaseResponse.builder()
                .uuid(UUID.randomUUID().toString())
                .status(HttpStatus.OK.value())
                .data(authorService.getAuthorById(id)).build());
    }
    @GetMapping("/detail/{id}")
    public ResponseEntity<?> getDetailById(@PathVariable Long id) {
        return ResponseEntity.ok(BaseResponse.builder()
                .uuid(UUID.randomUUID().toString())
                .status(HttpStatus.OK.value())
                .data(authorService.getDetailedAuthor(id))
                .build());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        authorService.deleteAuthor(id);
        return ResponseEntity
                .noContent()
                .build(); // status 204 gedir
    }
}
