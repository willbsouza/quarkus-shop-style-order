package br.com.compass.msorder.domain.model;

import br.com.compass.msorder.domain.model.enums.Status;
import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@MongoEntity(collection = "order")
@Getter
@Setter
@NoArgsConstructor
public class Order {

    @BsonId
    private ObjectId id;
    @NotNull
    private Customer customer;
    @NotNull
    private Address address;
    @NotNull
    private Payment payment;
    @NotNull
    private Installment installment;
    @NotNull
    private List<Sku> cart;
    @NotNull
    private LocalDate date;
    @NotNull
    private Status status;
    @NotNull
    private Double total;
}
