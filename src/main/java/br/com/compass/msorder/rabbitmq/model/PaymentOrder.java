package br.com.compass.msorder.rabbitmq.model;

import br.com.compass.msorder.resources.dto.PaymentDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class PaymentOrder implements Serializable {
    private static final long serialVersionUID = 1L;

    private String orderId;
    private PaymentDto payment;
}
