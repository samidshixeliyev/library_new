package ao.samid.library.dto.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class CategoryCreateRequest implements Serializable {
    private String name;
}
