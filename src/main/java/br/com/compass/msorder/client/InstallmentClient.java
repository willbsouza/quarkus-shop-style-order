package br.com.compass.msorder.client;

import br.com.compass.msorder.domain.model.Installment;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Singleton
@Path("/v1/installments")
@RegisterRestClient
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface InstallmentClient {

    @GET
    @Path("/order/{installmentId}")
    Installment getInstallments(@PathParam("installmentId") Long installmentId);
}
