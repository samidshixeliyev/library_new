package ao.samid.library.mapper;

import ao.samid.library.dto.request.AuthorCreateRequest;
import ao.samid.library.dto.request.AuthorUpdateRequest;
import ao.samid.library.dto.response.AuthorDetailedResponse;
import ao.samid.library.dto.response.AuthorResponse;
import ao.samid.library.dto.response.BookResponse;
import ao.samid.library.entity.Author;
import ao.samid.library.entity.Book;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;


@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface AuthorMapper {

    @Mapping(target = "name", source = "name") //target ve source basa dusum deye yazmisam eyni olanda fieldler yazmiriq
    Author fromCreateToEntity(AuthorCreateRequest request);
    @Mapping(target = "id", ignore = true, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void fromUpdateToEntity(@MappingTarget Author author, AuthorUpdateRequest request);

    AuthorResponse fromEntityToResponse(Author author);

    @Mapping(target = "books", expression = "java(bookListToBookResponseList(author.getBooks()))")
    AuthorDetailedResponse fromEntityToDetailedResponse(Author author);


    public default List<BookResponse> bookListToBookResponseList(List<Book> list) {
        if (list == null) {
            return null;
        } else {
            List<BookResponse> list1 = new ArrayList(list.size());
            for(Book book : list) {
                list1.add(fromEntityToResponse(book));
            }
            return list1;
        }
    }

    public default BookResponse fromEntityToResponse(Book book) {
        if (book == null) {
            return null;
        } else {
            BookResponse bookResponse = new BookResponse();
            bookResponse.setId(book.getId());
            bookResponse.setTitle(book.getTitle());
            bookResponse.setDescription(book.getDescription());
            return bookResponse;
        }
    }

}
