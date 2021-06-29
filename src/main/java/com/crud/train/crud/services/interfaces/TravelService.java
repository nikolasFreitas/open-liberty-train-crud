package com.crud.train.crud.services.interfaces;

import java.util.Optional;
import java.util.UUID;

import javax.ejb.Local;

import com.crud.train.crud.api.dto.CreateTravelDTO;
import com.crud.train.crud.repository.Entity.Travel;

@Local
public interface TravelService {
  public Optional<UUID> create(CreateTravelDTO travel);
  public Optional<Travel> getTravel(UUID travel);
}
