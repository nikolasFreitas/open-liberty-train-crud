package com.crud.train.crud.api.controller;

import java.util.Set;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
  @Transactional
  public Response create(CreatePassangerDTO passangerDTO) {
    Response errorResponse = responseUtil.validateRequest(passangerDTO);

    if (errorResponse != null) {
      return errorResponse;
    }

    Passanger passanger = new Passanger();
    
    passanger.setName(passangerDTO.getName());
    passanger.setEmail(passangerDTO.getEmail());
    var passangerUuid = passangerService.create(passanger);
    return responseUtil.UUIDResponseFormat(passangerUuid);
  }
}
