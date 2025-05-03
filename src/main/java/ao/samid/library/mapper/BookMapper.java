package ao.samid.library.mapper;

import ao.samid.library.dto.request.BookCreateRequest;
import ao.samid.library.dto.request.BookUpdateRequest;
import ao.samid.library.dto.response.BookResponse;
import ao.samid.library.entity.Author;
import ao.samid.library.entity.Book;
import ao.samid.library.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {AuthorMapper.class, CategoryMapper.class})
public interface BookMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Book fromCreateToEntity (BookCreateRequest request, Author author, Category category);
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void fromUpdateToEntity (BookUpdateRequest request, @MappingTarget Book book, Author author, Category category);
    BookResponse fromEntityToResponse(Book book);

}
