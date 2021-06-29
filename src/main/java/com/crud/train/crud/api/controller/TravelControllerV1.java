package com.crud.train.crud.api.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.crud.train.crud.api.dto.CreateTravelDTO;
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

  
}
