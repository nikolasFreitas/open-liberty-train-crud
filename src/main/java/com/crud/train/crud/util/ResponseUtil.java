package com.crud.train.crud.util;

import java.util.Set;
import java.util.UUID;

import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.crud.train.crud.api.dto.ResponseDTO;
import com.crud.train.crud.api.dto.UUIDResponseDTO;

import lombok.Data;

@Data
public class ResponseUtil {
  @Inject
  private ResponseDTO responseDto;

  @Inject
  private ValidatorFactory validatorFactory;

  public <T> Response formatBadrequest(Set<ConstraintViolation<T>> constraintViolations) {
    constraintViolations.stream().forEach((outroConstraint) -> {
      responseDto.addError(outroConstraint.getMessageTemplate());
    });
    var response = Response.status(Status.BAD_REQUEST)
                    .entity(responseDto)
                    .build();
    return response;
  }

  public <T> Response formatCreate(T responseDTO) {
    responseDto.setData(responseDTO);
    var response = Response.status(Status.CREATED)
      .entity(responseDto)
      .build();

    return response;
  }

  public <T> Response UUIDResponseFormat(UUID uuid) {
    responseDto.setData(new UUIDResponseDTO(uuid));
    var response = Response.status(Status.CREATED)
    .entity(responseDto)
    .build();

    return response;
  }

  public <T> Response customFormat(Status httpStatus, T responseDTO) {
    responseDto.setData(responseDTO);
    var response = Response.status(httpStatus)
      .entity(responseDto)
      .build();

    return response;
  }

  public <T> Response validateRequest(T requestBody) {
    Validator validator = validatorFactory.getValidator();
    Set<ConstraintViolation<T>> constraintViolations = validator.validate(requestBody);

    if (!constraintViolations.isEmpty()) {
      return this.formatBadrequest(constraintViolations);
    }

    return null;
  }
}
