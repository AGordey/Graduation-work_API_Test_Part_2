package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MorfeusBody {

    public MorfeusBody(String name, String job) {
        this.name = name;
        this.job = job;
    }

    private String name;
    private String job;
}
