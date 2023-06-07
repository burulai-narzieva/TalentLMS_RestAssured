package api_test;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsersPojo {
    private String first_name;
    private String email;
    private String id;
}
