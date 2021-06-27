package com.crud.train.crud.api.controller;


import java.util.Optional;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.validation.ValidationException;
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
import com.crud.train.crud.services.TrainServiceImpl;
import com.crud.train.crud.services.interfaces.TrainService;
import com.crud.train.crud.util.ResponseUtil;


@Path("/v1/train")
public class TrainControllerV1 {

  @EJB
  private TrainService trainService;

  @Inject
  private ResponseUtil responseUtil;
  
  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response createTrain(final CreateTrainDto trainDto) {
    try {
      Optional<Response> errorResponse = responseUtil.validateRequest(trainDto);

      if (errorResponse.isPresent()) {
        return errorResponse.get();
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
  @Path("/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getTrain(@PathParam("id") Long trainId) {
    try {
      var train = trainService.getDao().find(trainId);
      return responseUtil.customFormat(Status.OK, train);
    } catch (Exception e) {
      System.out.println(e);
      return Response.status(Status.NOT_FOUND).build();
    }
  }
}
