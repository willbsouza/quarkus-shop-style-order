package br.com.compass.msorder.client;

import br.com.compass.msorder.domain.model.Payment;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Singleton
@Path("/v1/payments")
@RegisterRestClient
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface PaymentClient {

    @GET
    @Path("/order/{paymentId}")
    Payment getPayment(@PathParam("paymentId") Long paymentId);
}
