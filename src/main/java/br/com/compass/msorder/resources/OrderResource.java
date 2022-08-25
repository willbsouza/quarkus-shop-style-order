package br.com.compass.msorder.resources;

import br.com.compass.msorder.domain.model.Order;
import br.com.compass.msorder.repository.OrderRepository;
import org.bson.types.ObjectId;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("/v1/orders")
public class OrderResource {

    private OrderRepository orderRepository;

    @Inject
    public OrderResource(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    @POST
    public Response create(Order order){
        orderRepository.persist(order);

        return Response.status(201).entity(order).build();
    }

    @GET
    public Response findAll(){
        return Response.ok(orderRepository.findAll().list()).build();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") ObjectId id){
        Order order = orderRepository.findById(id);
        if (order == null){
            return Response.status(404).entity("Order id: " + id + " not found.").build();
        }
        return Response.ok(order).build();
    }
}
