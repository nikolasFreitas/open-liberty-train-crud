package com.crud.train.crud.client;


import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.crud.train.crud.Repository.DAO.TrainDAO;
import com.crud.train.crud.Repository.Entity.Train;

@RequestScoped
@Path("/v1/train")
public class TrainControllerV1 {

  @Inject
  private TrainDAO trainDao;
  
  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Train createTrain(Train train) {    
    return trainDao.create(train);
  }
}
