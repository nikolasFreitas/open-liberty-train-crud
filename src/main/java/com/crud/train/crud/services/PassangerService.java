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
  
  public UUID create(Passanger passanger) {
    return UUID.fromString(passangerDao.create(passanger).getId());
  }
  
  public PassangerDAO getDao() {
    return passangerDao;
  }
}
