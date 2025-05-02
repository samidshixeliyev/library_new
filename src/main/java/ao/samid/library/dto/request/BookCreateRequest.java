package ao.samid.library.dto.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class BookCreateRequest implements Serializable {
    private String title;
    private String description;
    private Long authorId;
    private Long categoryId;
}
