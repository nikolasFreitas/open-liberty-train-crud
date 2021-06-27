package com.crud.train.crud.services.interfaces;

import java.util.Optional;
import java.util.UUID;

import javax.ejb.Local;

import com.crud.train.crud.repository.Entity.Passenger;
import com.crud.train.crud.repository.dao.PassengerDAO;

@Local
public interface PassengerService {
  public Optional<UUID> create(Passenger newPassenger);
  public PassengerDAO getDao();
  public Passenger getPassanger(UUID passengerUuid);
  public Optional<Passenger> getPassangerByEmail(String email);
}
