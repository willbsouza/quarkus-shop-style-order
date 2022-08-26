package br.com.compass.msorder.domain.model;

import br.com.compass.msorder.domain.model.enums.State;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Address {

    private Long id;
    private State state;
    private String city;
    private String district;
    private String street;
    private String number;
    private String complement;
    private String cep;
}
