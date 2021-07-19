package com.crud.train.crud.services;

import com.crud.train.crud.repository.Entity.Train;
import com.crud.train.crud.repository.Entity.Travel;
import com.crud.train.crud.repository.dao.TrainDAO;
import com.crud.train.crud.services.exceptions.TrainNotAvailableToTravelException;
import com.crud.train.crud.services.interfaces.TrainService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.Optional;

@Stateless
public class TrainServiceImpl implements TrainService {
  @Inject
  private TrainDAO trainDao;

  @Override
  public Train create(Train train) {
    return trainDao.create(train);
  }

  @Override
  public TrainDAO getDao() {
    return trainDao;
  }

  @Override
  public Optional<Train> getTrainIfAvailableToTravel(Long id, LocalDateTime departureDateTime, LocalDateTime arrivalDateTime) throws TrainNotAvailableToTravelException {
    Optional<Train> trainOptional = trainDao.find(id);
    if (trainOptional.isPresent()) {
      Train train = trainOptional.get();
      if (train.getTravelList().isEmpty()) {
        return trainOptional;
      }

      Optional<Travel> travelBetweenDates = train.getTravelList()
                                  .stream()
                                  .filter(travel -> travel.getArrivelDateTime().isAfter(departureDateTime) && travel.getDepartureDateTime().isBefore(arrivalDateTime))
                                  .findFirst();

      if (travelBetweenDates.isPresent()) {
        throw new TrainNotAvailableToTravelException(departureDateTime);
      }

      return trainOptional;
    }

    return Optional.empty();
  }

  @Override
  public Optional<Train> addTrainToTravel(Long id, Travel travel) {
    throw new UnsupportedOperationException("This methods is not implemented yet");
    // Optional<Train> trainOptional = trainDao.find(id);
    // if (trainOptional.isPresent()) {
    //   trainOptional.get().addTravel(travel);
    //   return trainOptional;
    // }
    
    
    // return Optional.empty();
  }
}
