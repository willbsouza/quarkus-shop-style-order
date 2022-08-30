package br.com.compass.msorder.rabbitmq;

import br.com.compass.msorder.rabbitmq.model.PaymentOrder;
import br.com.compass.msorder.rabbitmq.model.SkuOrder;
import io.vertx.core.json.JsonObject;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class RabbitMQProducer {

    @Channel("order-sku")
    Emitter<JsonObject> emitterOrderSku;
    @Channel("order-payment")
    Emitter<JsonObject> emitterOrderPayment;

    public void publishMessage(SkuOrder message){
        emitterOrderSku.send(JsonObject.mapFrom(message));
    }

    public void publishMessage(PaymentOrder message){
        emitterOrderPayment.send(JsonObject.mapFrom(message));
    }
}
