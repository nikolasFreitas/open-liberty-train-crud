package com.crud.train.crud.services;

import java.util.UUID;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.NoResultException;

import com.crud.train.crud.repository.Entity.Passanger;
import com.crud.train.crud.repository.dao.PassangerDAO;

@Stateless
public class PassangerService {

  @Inject
  private PassangerDAO passangerDao;
  
  public UUID create(Passanger newPassanger) {
    try {
      Boolean passangerAlreadyExists = passangerDao.find("email", newPassanger.getEmail()) != null;
      if (passangerAlreadyExists) {
        return null;
      }
    } catch (NoResultException e) {
      System.out.println(e);
    }
    return UUID.fromString(passangerDao.create(newPassanger).getUuid());
  }
  
  public PassangerDAO getDao() {
    return passangerDao;
  }

  public Passanger getPassanger(UUID passangeUuid) {
    return passangerDao.find(passangeUuid.toString());
  }
}
