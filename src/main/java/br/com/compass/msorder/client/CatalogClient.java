package br.com.compass.msorder.client;

import br.com.compass.msorder.domain.model.Sku;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Singleton
@Path("/v1/skus")
@RegisterRestClient
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface CatalogClient {

    @GET
    @Path("/order/{skuId}")
    Sku getSku(@PathParam("skuId") Long skuId);
}
