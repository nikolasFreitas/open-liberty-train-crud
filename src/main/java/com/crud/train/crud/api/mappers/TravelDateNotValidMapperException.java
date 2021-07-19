package com.crud.train.crud.api.mappers;

import com.crud.train.crud.api.dto.ResponseDTO;
import com.crud.train.crud.services.exceptions.TravelDateNotValidException;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.logging.Level;
import java.util.logging.Logger;

@Provider
public class TravelDateNotValidMapperException implements ExceptionMapper<TravelDateNotValidException> {
    private static final Logger LOGGER = Logger.getLogger(TravelDateNotValidException.class.getName());

    @Override
    public Response toResponse(TravelDateNotValidException exception) {
        ResponseDTO responseDto = new ResponseDTO();
        responseDto.addError(exception.getMessage());

        LOGGER.log(Level.WARNING,exception.getMessage());
        return Response
                .status(Response.Status.BAD_REQUEST)
                .type(MediaType.APPLICATION_JSON)
                .entity(responseDto)
                .build();
    }
}
