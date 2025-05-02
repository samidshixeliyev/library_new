package ao.samid.library.dto.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class BookUpdateRequest implements Serializable {
    private Long id;
    private String title;
    private String description;
    private Long authorId;
    private Long categoryId;
}
