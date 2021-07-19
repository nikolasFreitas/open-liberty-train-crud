package com.crud.train.crud.api.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class  CreatePassengerDTO {

  @NotBlank(message = "Add a name to passanger")
  @NotNull
  private String name;

  @Email(message = "All passangers must have an valid E-mail")
  @NotBlank(message = "Include an e-mail")
  @NotNull
  private String email;
}
