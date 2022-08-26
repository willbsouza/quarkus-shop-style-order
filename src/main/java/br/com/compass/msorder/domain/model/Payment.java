package br.com.compass.msorder.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Payment {

    private Long id;
    private String type;
    private Boolean active;
    private Boolean installments;
}
