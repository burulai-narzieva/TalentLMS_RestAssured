package com.digital.entities_pojo;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)   // если из JSON приходить поля которые мы здесь
// не указали, он не будет ругаться, а просто даст exception о том что нет такие поля
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)     //
public class User {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    String id;
    @JsonProperty("first_name")
    String firstName;
    @JsonProperty("last_name")
    String lastName;
    String email;
    @JsonInclude(JsonInclude.Include.NON_NULL)   // поля не всегда используется
    String login;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    String password;




}
