package com.crud.train.crud.services;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.crud.train.crud.repository.Entity.Route;
import com.crud.train.crud.repository.dao.RouteDAO;
import com.crud.train.crud.services.interfaces.RouteService;

@Stateless
public class RouteServiceImpl implements RouteService {
  @Inject
  private RouteDAO routeDao;

  @Override
  public Route create(Route route) {
    Optional<Route> routeOption = findRouteByOriginAndDestiny(route.getOriginCity(), route.getDestinyCity());

    if (routeOption.isPresent()) {
      var repeatedRoute = routeOption.get();
      StringBuffer repeatedRouteLog = new StringBuffer("Route with")
        .append(" destiny city: " + repeatedRoute.getDestinyCity())
        .append(" and origin city: " + repeatedRoute.getOriginCity());

      System.out.println(repeatedRouteLog);

      return repeatedRoute;
    }

    return routeDao.create(route);
  }

  @Override
  public RouteDAO getDao() {
    return routeDao;
  }

  @Override
  public Optional<Route> findRouteByOriginAndDestiny(String originCity, String destinyCity) {
    Map<String, String> filterItems = new HashMap<>();
    filterItems.put("originCity", originCity);
    filterItems.put("destinyCity", destinyCity);

    Optional<Route> routeOption = routeDao.find(filterItems);
    return routeOption;
  }
}
