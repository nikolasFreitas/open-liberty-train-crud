package com.crud.train.crud.api.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class  CreatePassengerDTO {

  @NotBlank(message = "Add a name to passanger")
  private String name;

  @Email(message = "All passangers must have an valid E-mail")
  @NotBlank(message = "Include an e-mail")
  private String email;
}
