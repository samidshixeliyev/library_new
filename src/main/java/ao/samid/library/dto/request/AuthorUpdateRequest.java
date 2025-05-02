package ao.samid.library.dto.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class AuthorUpdateRequest implements Serializable {
    private String name;
    private Long id;
}
