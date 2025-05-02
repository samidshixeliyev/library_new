package ao.samid.library.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookResponse implements Serializable {
    private Long id;
    private String title;
    private String description;
    private AuthorResponse author;
    private CategoryResponse category;
//    private LocalDateTime createdAt;
//    private LocalDateTime updatedAt;

}
