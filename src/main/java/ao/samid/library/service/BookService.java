package ao.samid.library.service;
import ao.samid.library.dto.request.BookCreateRequest;
import ao.samid.library.dto.request.BookUpdateRequest;
import ao.samid.library.dto.response.BookResponse;
import ao.samid.library.entity.Author;
import ao.samid.library.entity.Book;
import ao.samid.library.entity.Category;
import ao.samid.library.handler.CustomException;
import ao.samid.library.mapper.BookMapper;
import ao.samid.library.repository.AuthorRepository;
import ao.samid.library.repository.BookRepository;
import ao.samid.library.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService implements IBookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final CategoryRepository categoryRepository;
    private final BookMapper bookMapper;
    @Override
    public List<BookResponse> getAllBooks (){
        // eger bos list qaytarmamaq telebi gelerse asaqidaki return bir variable qoyub sonra if else chek edirik
        return bookRepository.findAll().stream()
                .map(bookMapper::fromEntityToResponse)
                .collect(Collectors.toList());
    }
    @Override
    public BookResponse getBookById(Long id) {
        return bookRepository.findById(id)
                .map(bookMapper::fromEntityToResponse)
                .orElseThrow(() -> new RuntimeException("Book not found"));
    }
    @Override
    public void saveBook(BookCreateRequest request) {
        Author author = authorRepository.findById(request.getAuthorId()).orElseThrow(() -> CustomException.builder().message("Author not found").code(404).build());
        Category category = categoryRepository.findById(request.getCategoryId()).orElseThrow(() -> CustomException.builder().message("Category not found").code(404).build());
        bookRepository.save(bookMapper.fromCreateToEntity(request,author,category));
    }

    @Override
    public void updateBook(BookUpdateRequest request) {
        Book book = bookRepository.findById(request.getId()).orElseThrow(() -> new RuntimeException("Book not found"));
        Author author = authorRepository.findById(request.getAuthorId()).orElseThrow(() -> new RuntimeException("Author not found"));
        Category category = categoryRepository.findById(request.getCategoryId()).orElseThrow(() -> new RuntimeException("Category not found"));
        bookMapper.fromUpdateToEntity(request,book,author,category);
        bookRepository.save(book);
    }
    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}
