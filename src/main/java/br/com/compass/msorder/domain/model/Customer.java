package br.com.compass.msorder.domain.model;

import br.com.compass.msorder.domain.model.enums.Sex;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Customer {

    private Long id;
    private String firstName;
    private String lastName;
    private Sex sex;
    private String email;
    private Boolean active;
}
