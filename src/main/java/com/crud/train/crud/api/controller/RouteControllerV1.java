package com.crud.train.crud.api.controller;

import java.util.Optional;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.crud.train.crud.api.dto.CreateRouteDTO;
import com.crud.train.crud.repository.Entity.Route;
import com.crud.train.crud.services.interfaces.RouteService;
import com.crud.train.crud.util.ResponseUtil;

@RequestScoped
@Path("/v1/route")
public class RouteControllerV1 {

  @EJB
  private RouteService routeService;

  @Inject
  private ResponseUtil responseUtil;
  
  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response create(@Valid CreateRouteDTO createRouteDTO) {
    Route route = new Route();
    route.setOriginCity(createRouteDTO.getOriginCity());
    route.setDestinyCity(createRouteDTO.getDestinyCity());

    return responseUtil.formatCreate(routeService.create(route).getId());
  }
}
