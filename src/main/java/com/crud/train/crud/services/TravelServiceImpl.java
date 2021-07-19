package com.crud.train.crud.services;

import com.crud.train.crud.api.dto.CreateTravelDTO;
import com.crud.train.crud.repository.Entity.Route;
import com.crud.train.crud.repository.Entity.Train;
import com.crud.train.crud.repository.Entity.Travel;
import com.crud.train.crud.repository.dao.TravelDAO;
import com.crud.train.crud.services.exceptions.TrainNotAvailableToTravelException;
import com.crud.train.crud.services.exceptions.TravelDateNotValidException;
import com.crud.train.crud.services.interfaces.PassengerService;
import com.crud.train.crud.services.interfaces.RouteService;
import com.crud.train.crud.services.interfaces.TrainService;
import com.crud.train.crud.services.interfaces.TravelService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@SuppressWarnings("ALL")
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
  public Optional<UUID> create(CreateTravelDTO createTravelDTO) throws TrainNotAvailableToTravelException, TravelDateNotValidException {
    this.validTravelDate(createTravelDTO.getDepartureDateTime(), createTravelDTO.getArrivalDateTime());

    Optional<Route> route = routeService
                              .findRouteByOriginAndDestiny(createTravelDTO.getOriginCity(), createTravelDTO.getDestinyCity());
    Optional<Train> train = trainService
                              .getTrainIfAvailableToTravel(createTravelDTO.getTrainId(), createTravelDTO.getDepartureDateTime(), createTravelDTO.getArrivalDateTime());

    if (route.isEmpty() || train.isEmpty()) {
      return Optional.empty();
    }

    Travel travel = new Travel(route.get(), train.get(), createTravelDTO.getDepartureDateTime(), createTravelDTO.getArrivalDateTime());
    var travelOpt = travelDAO.create(travel);

    return Optional.of(UUID.fromString(travelOpt.getUuid()));
  }

  @Override
  public Optional<Travel> getTravel(UUID travel) {
    var entity = travelDAO.find(travel.toString());
    return Optional.ofNullable(entity);
  }

  private void validTravelDate(LocalDateTime departure, LocalDateTime arrival) throws TravelDateNotValidException {
    if (departure.isAfter(arrival) || departure.isEqual(arrival)) {
      throw new TravelDateNotValidException();
    }

    if (!departure.isAfter(LocalDateTime.now()) || !arrival.isAfter(LocalDateTime.now())) {
      String errorMessage = "Travel departure and arrival must be after now (" + LocalDate.now() + ")";
      throw new TravelDateNotValidException(errorMessage);
    }
  }
}
