package com.crud.train.crud.api.dto;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class CreateRouteDTO {

  @NotEmpty
  private String destinyCity;

  @NotEmpty
  private String originCity;
}
