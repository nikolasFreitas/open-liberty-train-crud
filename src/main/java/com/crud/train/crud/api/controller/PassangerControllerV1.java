package com.crud.train.crud.api.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

import com.crud.train.crud.api.dto.CreatePassangerDTO;
import com.crud.train.crud.repository.Entity.Passanger;
import com.crud.train.crud.services.PassangerService;
import com.crud.train.crud.util.ResponseUtil;

@Path("/v1/passanger")
public class PassangerControllerV1 {
  
  @Inject
  private ResponseUtil responseUtil;

  @Inject
  private PassangerService passangerService;

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response create(CreatePassangerDTO passangerDTO) {
    Response errorResponse = responseUtil.validateRequest(passangerDTO);

    if (errorResponse != null) {
      return errorResponse;
    }

    Passanger passanger = new Passanger();
    
    passanger.setName(passangerDTO.getName());
    passanger.setEmail(passangerDTO.getEmail());
    var passangerUuid = passangerService.create(passanger);
    if (passangerUuid != null) {
      return responseUtil.UUIDResponseFormat(passangerUuid);
    }

    List<String> errorList = new ArrayList<>();
    errorList.add("E-mail is not available");

    return responseUtil.formatBadRequest(errorList);
  }

  @GET
  @Path("/{uuid}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getPassanger(@PathParam("uuid") String passangerUuid) {
    try {
      Passanger passanger = passangerService.getPassanger(UUID.fromString(passangerUuid));
      if (passanger != null) {
        return responseUtil.customFormat(Status.OK, passanger);
      }
    } catch (IllegalArgumentException e) {
      List<String> messageList = new ArrayList<>();
      messageList.add(e.getMessage());

      return responseUtil.formatBadRequest(messageList);
    }


    String notFoundMessage = "User with ID: " + passangerUuid + " not found";
    return responseUtil.notFoundFormat(notFoundMessage);
  }
}
