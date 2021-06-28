package com.crud.train.crud.api.controller;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.crud.train.crud.services.interfaces.PassengerService;
import com.crud.train.crud.services.interfaces.RouteService;
import com.crud.train.crud.services.interfaces.TrainService;
import com.crud.train.crud.util.ResponseUtil;

@Path("/v1/travel")
public class TravelControllerV1 {
  @Inject
  private ResponseUtil responseUtil;


  @EJB
  private TrainService trainService;

  @EJB
  private RouteService routeService;

  @EJB 
  private PassengerService passangerService;

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response createTravel() {
    return Response.ok().build();
  }

  
}
