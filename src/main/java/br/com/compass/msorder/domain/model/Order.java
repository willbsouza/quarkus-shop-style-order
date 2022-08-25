package br.com.compass.msorder.domain.model;

import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;

@MongoEntity(collection = "order")
@Getter
@Setter
@NoArgsConstructor
public class Order {

    @BsonId
    private ObjectId id;
    private String name;
}
