package com.crud.train.crud.api.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.crud.train.crud.api.dto.CreateTravelDTO;
import com.crud.train.crud.repository.Entity.Travel;
import com.crud.train.crud.services.interfaces.TravelService;
import com.crud.train.crud.util.ResponseUtil;

@Path("/v1/travel")
public class TravelControllerV1 {
  @Inject
  private ResponseUtil responseUtil;

  @EJB
  private TravelService travelService;

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response createTravel(CreateTravelDTO createTravelDTO) {
    Optional<Response> badRequestResponse = responseUtil.validateRequest(createTravelDTO);

    if (badRequestResponse.isPresent()) {
      return badRequestResponse.get();
    }

    Optional<UUID> optionalTrainUUID = travelService.create(createTravelDTO);
    if (optionalTrainUUID.isPresent()) {
      return responseUtil.UUIDResponseFormat(optionalTrainUUID.get());
    }

    List<String> badRequestMsg = Arrays.asList("Unnable to create");
    return responseUtil.customFormat(Status.INTERNAL_SERVER_ERROR, badRequestMsg);
  }

  @GET
  @Path("/{uuid}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getTavel(@PathParam("uuid") String routeUUID) {
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
