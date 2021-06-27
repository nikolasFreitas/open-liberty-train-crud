package com.crud.train.crud.services.interfaces;

import javax.ejb.Local;

import com.crud.train.crud.repository.Entity.Train;
import com.crud.train.crud.repository.dao.TrainDAO;

@Local
public interface TrainService {
  public Train create(Train train);
  public TrainDAO getDao();
}
