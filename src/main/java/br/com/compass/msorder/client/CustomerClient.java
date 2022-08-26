package br.com.compass.msorder.client;

import br.com.compass.msorder.domain.model.Customer;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Singleton
@Path("/v1/customers")
@RegisterRestClient
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface CustomerClient {

    @GET
    @Path("/order/{customerId}")
    Customer getCustomer(@PathParam("customerId") Long customerId);

}
