package lombok;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Morfeus {
    private String name;
    private String job;
    private String updatedAt;
}
