package com.crud.train.crud.services;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.crud.train.crud.repository.Entity.Route;
import com.crud.train.crud.repository.dao.RouteDAO;
import com.crud.train.crud.services.interfaces.RouteService;

@Stateless
public class RouteServiceImpl implements RouteService {
  @Inject
  private RouteDAO routeDao;

  public Route create(Route travel) {
    return routeDao.create(travel);
  }

  public RouteDAO getDao() {
    return routeDao;
  }
}
