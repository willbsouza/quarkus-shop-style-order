package br.com.compass.msorder.resources.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class PaymentDto {
    @NotNull
    private Long id;
    @NotNull
    private Integer installments;
}
