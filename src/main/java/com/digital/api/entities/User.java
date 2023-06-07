package com.digital.api.entities;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
    String id;
    @JsonProperty("first_name")
    String firstName;
    @JsonProperty("last_name")
    String lastName;
    String email;



}
