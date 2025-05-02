package ao.samid.library.dto.response;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class CategoryResponse implements Serializable {
    private Long id;
    private String name;
//    private LocalDateTime createdAt;
//    private LocalDateTime updatedAt;
}
