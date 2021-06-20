package com.crud.train.crud.api.dto;

import java.util.ArrayList;
import java.util.List;

import javax.json.bind.annotation.JsonbNillable;

import lombok.Data;

@Data
@JsonbNillable(value = true)
public class ResponseDTO {

  private Object data = null;
  private List<String> errors =  new ArrayList<>();

  public void addError(String error) {
    errors.add(error);
  }
}
