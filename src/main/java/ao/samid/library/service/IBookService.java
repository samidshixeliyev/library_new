package ao.samid.library.service;

import ao.samid.library.dto.request.BookCreateRequest;
import ao.samid.library.dto.request.BookUpdateRequest;
import ao.samid.library.dto.response.BookResponse;
import ao.samid.library.entity.Book;

import java.util.List;

public interface IBookService {
    List<BookResponse> getAllBooks();
    Book getBookById(Long id);
    void saveBook(BookCreateRequest request);
    void updateBook(Long id, BookUpdateRequest request);
    void deleteBook(Long id);

    void saveBook(Book book);
}
