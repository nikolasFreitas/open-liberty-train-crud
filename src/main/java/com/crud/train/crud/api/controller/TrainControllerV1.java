package com.crud.train.crud.api.controller;


import java.util.Set;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.Valid;
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
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import com.crud.train.crud.api.ResponseFormat;
import com.crud.train.crud.api.dto.CreateTrainDto;
import com.crud.train.crud.repository.Entity.Train;
import com.crud.train.crud.repository.dao.TrainDAO;


@Path("/v1/train")
public class TrainControllerV1 {

  @Inject
  private TrainDAO trainDao;

  @Inject
  private ResponseFormat responseFormatter;

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
        constraintViolations.stream().forEach((outroConstraint) -> {
          System.out.println(outroConstraint.getMessageTemplate());
          responseFormatter.addError(outroConstraint.getMessageTemplate());
        });
        var response = Response.fromResponse(Response.status(Status.BAD_REQUEST).build()).entity(responseFormatter);

        return response.build();
      }
      
      var train = new Train();
      train.setLocomotiveModel(trainDto.getLocomotiveModel());
      train.setQtdWagons(trainDto.getQtdWagons());
  
      var persistedTrain = trainDao.create(train);
      responseFormatter.setData(persistedTrain);
      return Response.ok(responseFormatter).build();

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
      var train = trainDao.find(userId);
      return Response.ok(train).build();
    } catch (Exception e) {
      System.out.println(e);
      return Response.status(Status.NOT_FOUND).build();
    }

  }
}
