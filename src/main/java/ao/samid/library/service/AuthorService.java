package ao.samid.library.service;

import ao.samid.library.dto.request.AuthorCreateRequest;
import ao.samid.library.dto.request.AuthorUpdateRequest;
import ao.samid.library.dto.response.AuthorDetailedResponse;
import ao.samid.library.dto.response.AuthorResponse;
import ao.samid.library.entity.Author;
import ao.samid.library.mapper.AuthorMapper;
import ao.samid.library.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthorService implements IAuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;
    @Override
    public List<AuthorResponse> getAuthors() {
        return authorRepository.findAll()
                .stream()
                .map(authorMapper::fromEntityToResponse)
                .collect(Collectors.toList());
    }
    @Override
    public AuthorResponse getAuthorById(Long id) {
        return authorRepository.findById(id).map(authorMapper::fromEntityToResponse).orElseThrow(() -> new RuntimeException("Author not found"));
    }
    @Override
    public AuthorResponse saveAuthor(AuthorCreateRequest request) {
        Author author = authorMapper.fromCreateToEntity(request);
        return authorMapper.fromEntityToResponse(authorRepository.save(author));
    }
    @Override
    public void updateAuthor(AuthorUpdateRequest request) {
        Author author = authorRepository.findById(request.getId()).orElseThrow(() -> new RuntimeException("Author not found"));
        authorMapper.fromUpdateToEntity(author, request);
        authorRepository.save(author);
    }
    @Override
    public void deleteAuthor(Long id) {
        authorRepository.deleteById(id);
    }

    @Override
    public AuthorDetailedResponse getDetailedAuthor(Long id) {
        return  authorRepository
                .findById(id)
                .map(authorMapper::fromEntityToDetailedResponse)
                .orElseThrow(() -> new RuntimeException("Author not found"));
    }
}
