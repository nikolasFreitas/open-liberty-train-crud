package com.crud.train.crud.services;

import java.util.UUID;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.crud.train.crud.repository.Entity.Passanger;
import com.crud.train.crud.repository.dao.PassangerDAO;

@Stateless
public class PassangerService {

  @Inject
  private PassangerDAO passangerDao;
  
  public UUID create(Passanger newPassanger) {
    Boolean passangerAlreadyExists = passangerDao.find("email", newPassanger.getEmail()) != null;
    if (passangerAlreadyExists) {
      return null;
    }
    return UUID.fromString(passangerDao.create(newPassanger).getId());
  }
  
  public PassangerDAO getDao() {
    return passangerDao;
  }

  public Passanger getPassanger(UUID passangeUuid) {
    return passangerDao.find(passangeUuid.toString());
  }
}
