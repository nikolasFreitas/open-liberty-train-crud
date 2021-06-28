package com.crud.train.crud.services;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.NoResultException;

import com.crud.train.crud.repository.Entity.Passenger;
import com.crud.train.crud.repository.dao.PassengerDAO;
import com.crud.train.crud.services.interfaces.PassengerService;


@Stateless
public class PassengerServiceImpl implements PassengerService {

  @Inject
  private PassengerDAO passengerDao;
  
  public Optional<UUID> create(Passenger newPassenger) {
    try {
      Boolean passengerAlreadyExists = passengerDao.find("email", newPassenger.getEmail()) != null;
      if (passengerAlreadyExists) {
        return Optional.empty();
      }
    } catch (NoResultException e) {
      System.out.println(e);
    }
    return Optional.of(UUID.fromString(passengerDao.create(newPassenger).getUuid()));
  }
  
  public PassengerDAO getDao() {
    return passengerDao;
  }

  public Passenger getPassanger(UUID passengerUuid) {
    return passengerDao.find(passengerUuid.toString());
  }

  public Optional<Passenger> getPassangerByEmail(String email) {
    Map<String, String> entityAttr = new HashMap<>();
    entityAttr.put("email", email);

    return passengerDao.find(entityAttr);
  }
}
