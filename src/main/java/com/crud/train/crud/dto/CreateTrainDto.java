package com.crud.train.crud.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Setter
@Getter
@ToString
public class CreateTrainDto {
  @NotEmpty(message = "Model must no be empty")
  private String locomotiveModel;

  @NotNull(message = "Quantity of wagons must no be NULL")
  private Integer qtdWagons;
}
