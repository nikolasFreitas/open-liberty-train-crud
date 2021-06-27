package com.crud.train.crud.repository.dao;

import com.crud.train.crud.repository.Entity.Passenger;

public class PassengerDAO extends Repository<Passenger> {
  public PassengerDAO() {
    super(Passenger.class);
  }

  @Override
  public Passenger find(String id) {
    return em.find(Passenger.class, id);
  }
}
