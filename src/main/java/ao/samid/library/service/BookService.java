package ao.samid.library.service;
import ao.samid.library.dto.request.BookCreateRequest;
import ao.samid.library.dto.request.BookUpdateRequest;
import ao.samid.library.dto.response.BookResponse;
import ao.samid.library.entity.Book;
import ao.samid.library.mapper.BookMapper;
import ao.samid.library.repository.AuthorRepository;
import ao.samid.library.repository.BookRepository;
import ao.samid.library.repository.CategoryRepository;
import java.util.List;
import java.util.stream.Collectors;

public class BookService implements IBookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final CategoryRepository categoryRepository;
    private final BookMapper bookMapper;
    public BookService(BookRepository bookRepository, AuthorRepository authorRepository, CategoryRepository categoryRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.authorRepository=authorRepository;
        this.categoryRepository=categoryRepository;
        this.bookMapper=bookMapper;
    }
    @Override
    public List<BookResponse> getAllBooks (){
        return bookRepository.findAll().stream()
                .map(bookMapper::fromEntityToResponse)
                .collect(Collectors.toList());
    }
    @Override
    public Book getBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));
    }
    @Override
    public void updateBook(Long id, BookUpdateRequest request) {
    }
    @Override
    public void deleteBook(Long id) {
    }
    @Override
    public void saveBook(Book book) {
        bookRepository.save(book);
    }
}
