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

import com.crud.train.crud.api.dto.CreatePassengerDTO;
import com.crud.train.crud.repository.Entity.Passenger;
import com.crud.train.crud.services.interfaces.PassengerService;
import com.crud.train.crud.util.ResponseUtil;

@Path("/v1/passenger")
public class PassengerControllerV1 {
  
  @Inject
  private ResponseUtil responseUtil;

  @EJB
  private PassengerService passengerService;


  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response create(CreatePassengerDTO passengerDTO) {
    Optional<Response> errorResponse = responseUtil.validateRequest(passengerDTO);

    if (errorResponse.isPresent()) {
      return errorResponse.get();
    }

    Passenger passenger = new Passenger();
    
    passenger.setName(passengerDTO.getName());
    passenger.setEmail(passengerDTO.getEmail());
    var passangerUuid = passengerService.create(passenger);
    if (passangerUuid.isPresent()) {
      return responseUtil.UUIDResponseFormat(passangerUuid.get());
    }

    List<String> errorList = Arrays.asList("E-mail is not available");

    return responseUtil.formatBadRequest(errorList);
  }

  @GET
  @Path("/{uuid}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getPassenger(@PathParam("uuid") String passengerUuid) {
    try {
      Passenger passanger = passengerService.getPassanger(UUID.fromString(passengerUuid));
      if (passanger != null) {
        return responseUtil.customFormat(Status.OK, passanger);
      }
    } catch (IllegalArgumentException e) {
      List<String> messageList = Arrays.asList(e.getMessage());

      return responseUtil.formatBadRequest(messageList);
    }


    String notFoundMessage = "User with ID: " + passengerUuid + " not found";
    return responseUtil.notFoundFormat(notFoundMessage);
  }

  @GET
  @Path("/email/{email}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getPassengerByEmail(@PathParam("email") String email) {

    var passanger = passengerService.getPassangerByEmail(email);
    if (passanger.isPresent()) {
      return responseUtil.customFormat(Status.OK, passanger);
    }

    String notFoundMessage = "User with email: " + email + " not found";
    return responseUtil.notFoundFormat(notFoundMessage);
  }
}
