package com.crud.train.crud.services;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.crud.train.crud.api.ResponseFormat;
import com.crud.train.crud.repository.dao.TrainDAO;

import lombok.Data;

@Data
@Stateless
public class TrainService {
  @Inject
  private TrainDAO trainDao;

  @Inject
  private ResponseFormat responseFormatter;
}