package ao.samid.library.controller;

import ao.samid.library.dto.request.BookCreateRequest;
import ao.samid.library.dto.request.BookUpdateRequest;
import ao.samid.library.dto.response.BaseResponse;
import ao.samid.library.dto.response.BookResponse;
import ao.samid.library.entity.Book;
import ao.samid.library.service.BookService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/book")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;
    @PostMapping
    public ResponseEntity<?> createBook(@RequestBody BookCreateRequest request) {
        bookService.saveBook(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @PutMapping
    public ResponseEntity<?> updateBook(@RequestBody BookUpdateRequest request) {
        bookService.updateBook(request);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/all")
    public ResponseEntity<?> getAllBooks() {
        return ResponseEntity.ok(BaseResponse.builder().uuid(UUID.randomUUID().toString())
                .data(bookService.getAllBooks())
                .status(HttpStatus.OK.value())
                .build());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getBookById(@PathVariable Long id) {
        return ResponseEntity.ok(BaseResponse.builder().uuid(UUID.randomUUID().toString())
                .data(bookService.getBookById(id))
                .status(HttpStatus.OK.value())
                .build());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBookById(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.ok().build();
    }
}
