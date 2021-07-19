package com.crud.train.crud.api.controller;

import com.crud.train.crud.api.dto.CreateTravelDTO;
import com.crud.train.crud.repository.Entity.Travel;
import com.crud.train.crud.services.interfaces.TravelService;
import com.crud.train.crud.util.ResponseUtil;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequestScoped
@Path("/v1/travel")
public class TravelControllerV1 {
  @Inject
  private ResponseUtil responseUtil;

  @EJB
  private TravelService travelService;

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response createTravel(@Valid CreateTravelDTO createTravelDTO) {
    Optional<UUID> optionalTrainUUID = travelService.create(createTravelDTO);

    if (optionalTrainUUID.isPresent()) {
      return responseUtil.UUIDResponseFormat(optionalTrainUUID.get());
    }

    return Response.serverError().build();
  }

  @GET
  @Path("/{uuid}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getTravel(@PathParam("uuid") String routeUUID) {
    try {
      Optional<Travel> travel = travelService.getTravel(UUID.fromString(routeUUID));
      if (travel.isPresent()) {   
        // Prevent stack overflow by infinity loop
        travel.get().getTrain().setTravelList(null);
        return responseUtil.customFormat(Status.OK, travel.get());
      }
    } catch (IllegalArgumentException e) {
      List<String> messageList = Arrays.asList(e.getMessage());

      return responseUtil.formatBadRequest(messageList);
    }

    String notFoundMessage = "User with ID: " + routeUUID + " not found";
    return responseUtil.notFoundFormat(notFoundMessage);
  }
}
