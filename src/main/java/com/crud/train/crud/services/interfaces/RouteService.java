package com.crud.train.crud.services.interfaces;

import java.util.Optional;

import javax.ejb.Local;

import com.crud.train.crud.repository.Entity.Route;
import com.crud.train.crud.repository.dao.RouteDAO;

@Local
public interface RouteService {
  public Route create(Route travel);
  public RouteDAO getDao();
  public Optional<Route> findRouteByOriginAndDestiny(String originCity, String destinyCity);
}
