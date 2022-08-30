package br.com.compass.msorder.rabbitmq.model;

import br.com.compass.msorder.domain.model.enums.Status;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PaymentOrderStatus implements Serializable {
    private static final long serialVersionUID = 1L;

    private String orderId;
    private Status status;
}

