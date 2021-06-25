package com.crud.train.crud.repository.dao;

import com.crud.train.crud.repository.Entity.Passanger;

public class PassangerDAO extends Repository<Passanger> {
  public PassangerDAO() {
    super(Passanger.class);
  }

  @Override
  public Passanger find(String id) {
    return em.find(Passanger.class, id);
  }
}
