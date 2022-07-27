package lombok;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Morfeus {
//    @JsonProperty("name")
    private String name;
//    @JsonProperty("job")
    private String job;
//    @JsonProperty("updatedAt")
    private String updatedAt;
}
