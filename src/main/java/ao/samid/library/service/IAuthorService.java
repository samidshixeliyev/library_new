package ao.samid.library.service;

import ao.samid.library.dto.request.AuthorCreateRequest;
import ao.samid.library.dto.request.AuthorUpdateRequest;
import ao.samid.library.dto.response.AuthorDetailedResponse;
import ao.samid.library.dto.response.AuthorResponse;
import ao.samid.library.entity.Author;

import java.util.List;

public interface IAuthorService {
    List<AuthorResponse> getAuthors();
    AuthorResponse getAuthorById(Long id);
    AuthorResponse saveAuthor(AuthorCreateRequest request);
    void updateAuthor(AuthorUpdateRequest request);
    void deleteAuthor(Long id);
    AuthorDetailedResponse getDetailedAuthor(Long id);
}
