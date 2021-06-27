package com.crud.train.crud.repository.dao;

import com.crud.train.crud.repository.Entity.Travel;

public class TravelDAO extends Repository<Travel> {
  public TravelDAO() {
    super(Travel.class);
  }

  @Override
  public Travel find(String id) {
    return em.find(Travel.class, id);
  }
}
