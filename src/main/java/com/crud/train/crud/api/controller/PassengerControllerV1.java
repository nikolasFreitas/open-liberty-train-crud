package com.crud.train.crud.api.controller;

import java.util.*;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.Valid;
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
@RequestScoped
public class PassengerControllerV1 {
  
  @Inject
  private ResponseUtil responseUtil;

  @EJB
  private PassengerService passengerService;


  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response create(@Valid CreatePassengerDTO passengerDTO) {
    Passenger passenger = new Passenger();
    
    passenger.setName(passengerDTO.getName());
    passenger.setEmail(passengerDTO.getEmail());
    var passengerUuid = passengerService.create(passenger);
    if (passengerUuid.isPresent()) {
      return responseUtil.UUIDResponseFormat(passengerUuid.get());
    }

    List<String> errorList = Collections.singletonList("E-mail is not available");

    return responseUtil.formatBadRequest(errorList);
  }

  @GET
  @Path("/{uuid}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getPassenger(@PathParam("uuid") String passengerUuid) {
    try {
      Passenger passenger = passengerService.getPassanger(UUID.fromString(passengerUuid));
      if (passenger != null) {
        return responseUtil.customFormat(Status.OK, passenger);
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

    var passenger = passengerService.getPassangerByEmail(email);
    if (passenger.isPresent()) {
      return responseUtil.customFormat(Status.OK, passenger);
    }

    String notFoundMessage = "User with email: " + email + " not found";
    return responseUtil.notFoundFormat(notFoundMessage);
  }
}
