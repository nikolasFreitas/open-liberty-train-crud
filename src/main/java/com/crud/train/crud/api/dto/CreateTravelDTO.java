package com.crud.train.crud.api.dto;

import java.time.LocalDate;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.crud.train.crud.repository.Entity.Route;

import lombok.Data;

@Data
public class CreateTravelDTO {
  @NotNull
  @Min(1)
  private Long trainId;

  @NotNull
  private Route route;

  @NotNull
  private LocalDate departure;

  @NotNull
  private LocalDate arrivalDate;
}
