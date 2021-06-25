package com.crud.train.crud.services;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.crud.train.crud.repository.Entity.Train;
import com.crud.train.crud.repository.dao.TrainDAO;

@Stateless
public class TrainService {
  @Inject
  private TrainDAO trainDao;

  public Train create(Train train) {
    return trainDao.create(train);
  }

  public TrainDAO getDao() {
    return trainDao;
  }
}
