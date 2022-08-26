package br.com.compass.msorder.repository;

import br.com.compass.msorder.domain.model.Order;
import io.quarkus.mongodb.panache.PanacheMongoRepository;
import io.quarkus.panache.common.Parameters;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.Map;

@ApplicationScoped
public class OrderRepository implements PanacheMongoRepository<Order> {

    public List<Order> findByCustomer(Long id){
        Map<String, Object> params = Parameters.with("id", id).map();
        return list("customer._id = :id", params);
    }
}
