package br.com.compass.msorder.resources.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class CustomerDto {
    @NotNull
    private Long id;
    @NotNull
    private Long addressId;
}
