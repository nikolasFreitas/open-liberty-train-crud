package com.crud.train.crud.api.dto;

import java.time.LocalDateTime;

import javax.json.bind.annotation.JsonbDateFormat;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class CreateTravelDTO {
  @NotNull
  @Min(1)
  private Long trainId;

  @NotBlank(message = "Destiny city must not be empty")
  private String destinyCity;

  @NotBlank(message = "Origin city must not be empty")
  private String originCity;

  @NotNull
  @JsonbDateFormat("HH:mm:ss dd-MM-yyyy")
  private LocalDateTime departureDateTime;

  @NotNull
  @JsonbDateFormat("HH:mm:ss dd-MM-yyyy")
  private LocalDateTime arrivalDateTime;
}
