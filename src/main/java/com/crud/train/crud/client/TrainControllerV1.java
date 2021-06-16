package com.crud.train.crud.client;


import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
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

import com.crud.train.crud.dto.CreateTrainDto;
import com.crud.train.crud.repository.Entity.Train;
import com.crud.train.crud.repository.dao.TrainDAO;


@Path("/v1/train")
@RequestScoped
public class TrainControllerV1 {

  @Inject
  private TrainDAO trainDao;
  
  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  @Transactional
  public Response createTrain(@Valid final CreateTrainDto trainDto) {
    System.out.println(trainDto);
    Train train = new Train();

    train.setLocomotiveModel(trainDto.getLocomotiveModel());
    train.setQtdWagons(trainDto.getQtdWagons());

    var persistedTrain = trainDao.create(train);
    return Response.ok(persistedTrain).build();
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
