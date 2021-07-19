package com.crud.train.crud.api.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class CreateRouteDTO {

  @NotEmpty
  @NotNull
  private String destinyCity;

  @NotEmpty
  @NotNull
  private String originCity;
}
