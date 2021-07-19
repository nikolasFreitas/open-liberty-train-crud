package com.crud.train.crud.api.mappers;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.crud.train.crud.api.dto.ResponseDTO;
import com.crud.train.crud.services.exceptions.TrainNotAvailableToTravelException;

@Provider
public class TrainNotAvailableToTravelExceptionMapper implements ExceptionMapper<TrainNotAvailableToTravelException>{
  private static final Logger LOGGER = Logger.getLogger(TrainNotAvailableToTravelException.class.getName());

    @Override
    public Response toResponse(TrainNotAvailableToTravelException exception) {
      ResponseDTO responseDto = new ResponseDTO();
      responseDto.addError(exception.getMessage());

      LOGGER.log(Level.WARNING,exception.getMessage());
      
      return Response.status(Response.Status.BAD_REQUEST)
                      .entity(responseDto)
                      .type(MediaType.APPLICATION_JSON)
                      .build();
    }

}