package ao.samid.library.dto.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class CategoryUpdateRequest implements Serializable {
    private Long id;
    private String name;
}
