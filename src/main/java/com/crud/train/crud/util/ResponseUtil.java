package com.crud.train.crud.util;

import java.util.Set;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.crud.train.crud.api.dto.ResponseDTO;

import lombok.Data;

@Data
public class ResponseUtil {
  @Inject
  private ResponseDTO responseDto;

  public <T> Response formatBadrequest(Set<ConstraintViolation<T>> constraintViolations) {
    constraintViolations.stream().forEach((outroConstraint) -> {
      responseDto.addError(outroConstraint.getMessageTemplate());
    });
    var response = Response
                    .fromResponse(Response.status(Status.BAD_REQUEST).build())
                    .entity(responseDto)
                    .build();
    return response;
  }

  public <T> Response formatCreate(T responseDTO) {
    responseDto.setData(responseDTO);
    var response = Response
      .fromResponse(Response.status(Status.CREATED).build())
      .entity(responseDto)
      .build();

    return response;
  }

  public <T> Response customFormat(Status httpStatus, T responseDTO) {
    responseDto.setData(responseDTO);
    var response = Response
      .fromResponse(Response.status(httpStatus).build())
      .entity(responseDto)
      .build();

    return response;
  }
}
