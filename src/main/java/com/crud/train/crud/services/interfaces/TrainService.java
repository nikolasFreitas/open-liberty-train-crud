package com.crud.train.crud.services.interfaces;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.ejb.Local;

import com.crud.train.crud.repository.Entity.Train;
import com.crud.train.crud.repository.Entity.Travel;
import com.crud.train.crud.repository.dao.TrainDAO;
import com.crud.train.crud.services.exceptions.TrainNotAvailableToTravelException;

@Local
public interface TrainService {
  public Train create(Train train);
  public TrainDAO getDao();
  public Optional<Train> getTrainIfAvailableToTravel(Long id, LocalDateTime departureDateTime, LocalDateTime arrivalDateTime) throws TrainNotAvailableToTravelException;
  public Optional<Train> addTrainToTravel(Long id, Travel travel);
}
