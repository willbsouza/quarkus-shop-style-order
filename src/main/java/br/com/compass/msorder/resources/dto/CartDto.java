package br.com.compass.msorder.resources.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class CartDto {
    @NotNull
    private Long skuId;
    @NotNull
    private Integer quantity;
}
