package br.com.compass.msorder.client;

import br.com.compass.msorder.domain.model.Address;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Singleton
@Path("/v1/addresses")
@RegisterRestClient
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface AddressClient {

    @GET
    @Path("/order/{addressId}")
    Address getAddress(@PathParam("addressId") Long addressId);
}
