package ao.samid.library.dto.response;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class AuthorDetailedResponse implements Serializable {
    private Long id;
    private String name;
    private List<BookResponse> books;
}
