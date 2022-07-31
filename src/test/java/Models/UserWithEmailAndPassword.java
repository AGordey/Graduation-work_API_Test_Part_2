package Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserWithEmailAndPassword {

    public UserWithEmailAndPassword(String email, String password) {
        this.email = email;
        this.password = password;
    }

    private String email;
    private String password;
}
