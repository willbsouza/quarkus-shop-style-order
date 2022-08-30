package br.com.compass.msorder.rabbitmq;

import br.com.compass.msorder.domain.model.Order;
import br.com.compass.msorder.rabbitmq.model.PaymentOrderStatus;
import br.com.compass.msorder.repository.OrderRepository;
import io.vertx.core.json.JsonObject;
import org.bson.types.ObjectId;
import org.eclipse.microprofile.reactive.messaging.Incoming;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class RabbitMQConsumer {

    @Inject
    private RabbitMQProducer rabbitMQProducer;

    private OrderRepository orderRepository;

    @Inject
    public RabbitMQConsumer(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    @Incoming("payment-order")
    public void processMessage(JsonObject msg){
        PaymentOrderStatus paymentOrderStatus = msg.mapTo(PaymentOrderStatus.class);
        updateStatusPayment(paymentOrderStatus);
    }

    private void updateStatusPayment(PaymentOrderStatus paymentOrderStatus) {
        Order order = orderRepository.findById(new ObjectId(paymentOrderStatus.getOrderId()));
        order.setStatus(paymentOrderStatus.getStatus());
        orderRepository.update(order);
        rabbitMQProducer.publishMessage(order);
    }
}
