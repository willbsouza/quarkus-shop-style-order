package br.com.compass.msorder.resources.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@NoArgsConstructor
public class OrderFormDto {
    @NotNull
    private CustomerDto customer;
    @NotNull
    private PaymentDto payment;
    @NotNull
    private List<CartDto> cart;
}
