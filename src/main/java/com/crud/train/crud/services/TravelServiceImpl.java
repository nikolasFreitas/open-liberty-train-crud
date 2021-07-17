package com.crud.train.crud.services;

import java.util.Optional;
import java.util.UUID;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.crud.train.crud.api.dto.CreateTravelDTO;
import com.crud.train.crud.repository.Entity.Route;
import com.crud.train.crud.repository.Entity.Train;
import com.crud.train.crud.repository.Entity.Travel;
import com.crud.train.crud.repository.dao.TravelDAO;
import com.crud.train.crud.services.interfaces.PassengerService;
import com.crud.train.crud.services.interfaces.RouteService;
import com.crud.train.crud.services.interfaces.TrainService;
import com.crud.train.crud.services.interfaces.TravelService;

@Stateless
public class TravelServiceImpl implements TravelService {
  @Inject
  private TravelDAO travelDAO;

  @EJB
  private PassengerService passengerService;

  @EJB
  private TrainService trainService;

  @EJB
  private RouteService routeService;

  @EJB 
  private PassengerService passangerService;

  @Override
  public Optional<UUID> create(CreateTravelDTO createTravelDTO) {
    Optional<Route> route = routeService.findRouteByOriginAndDestiny(createTravelDTO.getOriginCity(), createTravelDTO.getDestinyCity());
    Optional<Train> train = trainService.getTrainIfAvailableToTravel(createTravelDTO.getTrainId(), createTravelDTO.getDepartureDateTime());

    if (route.isEmpty() || train.isEmpty()) {
      return Optional.empty();
    }

    Travel travel = new Travel(route.get(), train.get(), createTravelDTO.getDepartureDateTime(), createTravelDTO.getArrivalDateTime());
    var travelOpt = Optional.of(travelDAO.create(travel));
    if (travelOpt.isEmpty()) {
      return Optional.empty();
    }
    return Optional.of(UUID.fromString(travelOpt.get().getUuid()));
  }

  @Override
  public Optional<Travel> getTravel(UUID travel) {
    var entity = travelDAO.find(travel.toString());
    return Optional.ofNullable(entity);
  }
}
