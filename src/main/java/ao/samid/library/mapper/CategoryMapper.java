package ao.samid.library.mapper;

import ao.samid.library.dto.request.CategoryCreateRequest;
import ao.samid.library.dto.request.CategoryUpdateRequest;
import ao.samid.library.dto.response.BookResponse;
import ao.samid.library.dto.response.CategoryDetailedResponse;
import ao.samid.library.dto.response.CategoryResponse;
import ao.samid.library.entity.Book;
import ao.samid.library.entity.Category;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;


import java.util.ArrayList;
import java.util.List;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface CategoryMapper {
    Category fromCreateToEntity(CategoryCreateRequest request);
    @Mapping(target = "id" , ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Category fromUpdateToEntity(CategoryUpdateRequest request,@MappingTarget Category target);
    CategoryResponse fromEntityToResponse(Category entity);
    // BookMapper detail responseda book qayidacaq deye map edirem
    @Mapping(target = "books", expression = "java(bookListToBookResponseList(entity.getBooks()))")
    CategoryDetailedResponse fromEntityToDetailedResponse(Category entity);

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
