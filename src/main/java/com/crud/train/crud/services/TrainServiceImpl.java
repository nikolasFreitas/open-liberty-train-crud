package com.crud.train.crud.services;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.crud.train.crud.repository.Entity.Train;
import com.crud.train.crud.repository.Entity.Travel;
import com.crud.train.crud.repository.dao.TrainDAO;
import com.crud.train.crud.services.interfaces.TrainService;

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
  public Optional<Train> getTrainIfAvailableToTravel(Long id, LocalDateTime departurDateTime) {
    Optional<Train> trainOptional = trainDao.find(id);
    if (trainOptional.isPresent()) {
      Train train = trainOptional.get();
      if (train.getTravelList().isEmpty()) {
        return trainOptional;
      }

      Travel lastTravel = train.getTravelList().get(train.getTravelList().size() - 1);
      if (lastTravel.getArrivelDateTime().isAfter(departurDateTime)) {
        return trainOptional;
      }

      System.out.println("The train is not available to start a travel in " + departurDateTime);
    }

    return Optional.empty();
  }

  @Override
  public Optional<Train> addTrainToTravel(Long id, Travel travel) {
    Optional<Train> trainOptional = trainDao.find(id);
    if (trainOptional.isPresent()) {
      trainOptional.get().addTravel(travel);
      return trainOptional;
    }
    
    
    return Optional.empty();
  }
}
