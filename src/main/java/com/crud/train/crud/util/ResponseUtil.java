package com.crud.train.crud.util;

import java.util.List;
import java.util.Optional;
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

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class ResponseUtil {

  @Inject
  private ResponseDTO responseDto;

  @Inject
  private ValidatorFactory validatorFactory;

  public <T> Response formatBadRequest(Set<ConstraintViolation<T>> constraintViolations) {
    constraintViolations.stream().forEach((constraint) -> {
      responseDto.addError(constraint.getMessageTemplate());
    });
    var response = Response.status(Status.BAD_REQUEST)
                    .entity(responseDto)
                    .build();
    return response;
  }

  public Response formatBadRequest(List<String> messageList) {
    messageList.stream().forEach((message) -> {
      responseDto.addError(message);
    });

    var response = Response.status(Status.BAD_REQUEST)
                    .entity(responseDto)
                    .build();
    return response;
  } 

  public <T> Response formatCreate(T customResponseDTO) {
    responseDto.setData(customResponseDTO);
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

  public <T> Response customFormat(Status httpStatus, T customResponseBody) {
    if (httpStatus.getStatusCode() < 300) {
      responseDto.setData(customResponseBody);
    } else {
      responseDto.addError(customResponseBody.toString());
    }
    var response = Response.status(httpStatus)
      .entity(responseDto)
      .build();

    return response;
  }

  public Response notFoundFormat(List<String> messageList) {
    messageList.stream().forEach(errorMessage -> {
      responseDto.addError(errorMessage);
    });
    var response = Response.status(Status.NOT_FOUND)
      .entity(responseDto)
      .build(); 
    
    return response;
  }

  public Response notFoundFormat(String message) {
    responseDto.addError(message);
    
    var response = Response.status(Status.NOT_FOUND)
      .entity(responseDto)
      .build(); 
    
    return response;
  }

  public <T> Optional<Response> validateRequest(T requestBody) {
    Validator validator = validatorFactory.getValidator();
    Set<ConstraintViolation<T>> constraintViolations = validator.validate(requestBody);

    if (!constraintViolations.isEmpty()) {
      return Optional.of(this.formatBadRequest(constraintViolations));
    }

    return Optional.empty();
  }
}
