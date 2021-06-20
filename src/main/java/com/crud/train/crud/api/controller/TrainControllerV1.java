package com.crud.train.crud.api.controller;


import java.util.Set;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.ValidationException;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.crud.train.crud.api.dto.CreateTrainDto;
import com.crud.train.crud.repository.Entity.Train;
import com.crud.train.crud.services.TrainService;
import com.crud.train.crud.util.ResponseUtil;


@Path("/v1/train")
public class TrainControllerV1 {

  @Inject
  private TrainService trainService;

  @Inject
  private ResponseUtil responseUtil;

  @Inject
  private ValidatorFactory validatorFactory;
  
  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  @Transactional
  public Response createTrain(final CreateTrainDto trainDto) {
    try {
      Validator validator = validatorFactory.getValidator();
      Set<ConstraintViolation<CreateTrainDto>> constraintViolations = validator.validate(trainDto);

      if (!constraintViolations.isEmpty()) {
        return responseUtil.formatBadrequest(constraintViolations);
      }

      var train = new Train();
      train.setLocomotiveModel(trainDto.getLocomotiveModel());
      train.setQtdWagons(trainDto.getQtdWagons());
  
      var persistedTrain = trainService.create(train);
      return responseUtil.formatCreate(persistedTrain);

    } catch (ValidationException e) {
      System.out.println(e.getMessage());
    }
    
    return Response.serverError().build();
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/{id}")
  @Transactional
  public Response getTrain(@PathParam("id") Long userId) {
    try {
      var train = trainService.getDao().find(userId);
      return responseUtil.customFormat(Status.OK, train);
    } catch (Exception e) {
      System.out.println(e);
      return Response.status(Status.NOT_FOUND).build();
    }

  }
}
