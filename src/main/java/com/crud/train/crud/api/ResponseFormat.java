package com.crud.train.crud.api;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class ResponseFormat {
  private Object data = null;
  private List<String> errors =  new ArrayList<>();

  public void addError(String error) {
    errors.add(error);
  }
}
