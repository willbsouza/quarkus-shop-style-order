package br.com.compass.msorder.resources.dto;

import br.com.compass.msorder.domain.model.*;
import br.com.compass.msorder.domain.model.enums.Status;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

import java.time.LocalDate;
import java.util.List;

@Getter
@NoArgsConstructor
public class OrderDto {
    private ObjectId id;
    private Customer customer;
    private List<Sku> cart;
    private Payment payment;
    private Installment installment;
    private Double total;
    private Address address;
    private LocalDate date;
    private Status status;

    public OrderDto(Order order) {
        this.id = order.getId();
        this.customer = order.getCustomer();
        this.payment = order.getPayment();
        this.cart = order.getCart();
        this.total = order.getTotal();
        this.status = order.getStatus();
        this.date = order.getDate();
        this.address = order.getAddress();
        this.installment = order.getInstallment();
    }
}
