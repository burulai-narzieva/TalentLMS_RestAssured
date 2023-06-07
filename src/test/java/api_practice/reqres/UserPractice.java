package api_practice.reqres;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
// Когда нам не нужно десерилизовывать всю структуру JSON'а мы вызываем аннотацию  JsonIgnoreProperties
// Нам достаточно получить пару каких-то параметров
@Getter
@Setter
public class UserPractice {
    // Класс который описывает структуру данных


    // Имя ключа должна соответствовать имя переменной
    @JsonProperty("id")
    private int id;
    @JsonProperty("email")
    private String email;
    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;
}
