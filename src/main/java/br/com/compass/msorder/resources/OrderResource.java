package br.com.compass.msorder.resources;

import br.com.compass.msorder.client.*;
import br.com.compass.msorder.domain.model.*;
import br.com.compass.msorder.domain.model.enums.Status;
import br.com.compass.msorder.repository.OrderRepository;
import br.com.compass.msorder.resources.dto.CartDto;
import br.com.compass.msorder.resources.dto.OrderDto;
import br.com.compass.msorder.resources.dto.OrderFormDto;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Path("/v1/orders")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class OrderResource {

    private OrderRepository orderRepository;

    @Inject
    @RestClient
    private CustomerClient customerClient;

    @Inject
    @RestClient
    private AddressClient addressClient;

    @Inject
    @RestClient
    private PaymentClient paymentClient;

    @Inject
    @RestClient
    private InstallmentClient installmentClient;

    @Inject
    @RestClient
    private CatalogClient catalogClient;

    @Inject
    public OrderResource(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

//    @POST
//    public Response create(Order order){
//        orderRepository.persist(order);
//
//        return Response.status(201).entity(order).build();
//    }

    @GET
    public Response findAll(@QueryParam(value = "startDate") LocalDate startDate,
                            @QueryParam(value = "endDate") LocalDate endDate,
                            @QueryParam(value = "status") Status status){

        Stream<Order> orderStream = orderRepository.findAll().stream().filter(o -> (o.getDate().isAfter(startDate) || o.getDate().isEqual(startDate)));
        if (startDate == null) {
            return Response.status(400).entity("StartDate is required param.").build();
        }
        if (endDate != null) {
            orderStream = orderStream.filter(o -> (o.getDate().isBefore(endDate) || o.getDate().isEqual(endDate)));
        }
        if (status != null) {
            orderStream = orderStream.filter(o -> (o.getStatus() == status));
        }
        return Response.ok(orderStream.map(OrderDto::new).collect(Collectors.toList())).build();
    }

    @GET
    @Path("/customers/{id}")
    public Response findByCustomerId(@PathParam("id") Long id,
                                     @QueryParam(value = "startDate") LocalDate startDate,
                                     @QueryParam(value = "endDate") LocalDate endDate,
                                     @QueryParam(value = "status") Status status){
        Customer customer = customerClient.getCustomer(id);
        if(customer == null){
            return Response.status(404).entity("Customer id: " + id + " not found.").build();
        }
        Stream<Order> orderStream = orderRepository.findByCustomer(customer.getId()).stream();
        if (status != null) {
            orderStream = orderStream.filter(o -> (o.getStatus() == status));
        }
        if (startDate != null) {
            orderStream = orderStream.filter(o -> (o.getDate().isAfter(startDate) || o.getDate().isEqual(startDate)));
        }
        if (endDate != null) {
            orderStream = orderStream.filter(o -> (o.getDate().isBefore(endDate) || o.getDate().isEqual(endDate)));
        }
        return Response.ok(orderStream.map(OrderDto::new).collect(Collectors.toList())).build();
    }

    @POST
    public Response create(@Valid OrderFormDto orderFormDto){
        Order order = new Order();
        Customer customer = customerClient.getCustomer(orderFormDto.getCustomer().getId());
        if (customer == null) {
            return Response.status(404).entity("Customer not found.").build();
        }
        if (!customer.getActive()) {
            return Response.status(404).entity("Customer is not active.").build();
        }
        Address address = addressClient.getAddress(orderFormDto.getCustomer().getAddressId());
        Payment payment = paymentClient.getPayment(orderFormDto.getPayment().getId());
        Installment installment = installmentClient.getInstallments(orderFormDto.getPayment().getId());
        installment.setAmount(orderFormDto.getPayment().getInstallments());
        installment.setPayment(payment);
        Double total = 0.0;
        List<Sku> cart = new ArrayList<>();
        for (CartDto cartDto : orderFormDto.getCart()) {
            Sku sku = catalogClient.getSku(cartDto.getSkuId());
            if(sku.getQuantity() >= cartDto.getQuantity()) {
                sku.setQuantity(cartDto.getQuantity());
            } else {
                return Response.status(400).entity("Quantity unavailable Sku ID: " + sku.getId()).build();
            }
            cart.add(sku);
            total += (sku.getPrice() * cartDto.getQuantity());
        }
        order.setCustomer(customer);
        order.setAddress(address);
        order.setPayment(payment);
        order.setInstallment(installment);
        order.setCart(cart);
        order.setDate(LocalDate.now());
        order.setStatus(Status.PROCESSING_PAYMENT);
        order.setTotal(total);

        orderRepository.persist(order);
        return Response.status(201).entity(new OrderDto(order)).build();
    }
}
