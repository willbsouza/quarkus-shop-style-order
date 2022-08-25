package br.com.compass.msorder.repository;

import br.com.compass.msorder.domain.model.Order;
import io.quarkus.mongodb.panache.PanacheMongoRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class OrderRepository implements PanacheMongoRepository<Order> {
}
