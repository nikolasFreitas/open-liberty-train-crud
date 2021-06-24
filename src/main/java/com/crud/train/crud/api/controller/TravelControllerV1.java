package com.crud.train.crud.api.controller;

import javax.inject.Inject;
import javax.validation.ValidatorFactory;
import javax.ws.rs.Path;

import com.crud.train.crud.repository.Entity.Passanger;
import com.crud.train.crud.services.PassangerService;
import com.crud.train.crud.services.RouteService;
import com.crud.train.crud.services.TrainService;
import com.crud.train.crud.util.ResponseUtil;

@Path("/v1/travel")
public class TravelControllerV1 {
  @Inject
  private ResponseUtil responseUtil;

  @Inject
  private ValidatorFactory validatorFactory;

  @Inject
  private TrainService trainService;

  @Inject
  private RouteService routeService;

  @Inject 
  private PassangerService passangerService;

  
}
