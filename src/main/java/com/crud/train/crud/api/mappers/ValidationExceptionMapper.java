package com.crud.train.crud.api.mappers;

import com.crud.train.crud.api.dto.ResponseDTO;

import javax.inject.Inject;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.logging.Logger;

@Provider
public class ValidationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {
    private static final Logger LOGGER = Logger.getLogger(ValidationExceptionMapper.class.getName());
    @Inject
    private ResponseDTO responseDto;

    @Override
    public Response toResponse(ConstraintViolationException exception) {
        var constraintViolations = exception.getConstraintViolations();

        if (constraintViolations != null) {
            constraintViolations.stream().forEach((constraint) -> {
                responseDto.addError(constraint.getMessageTemplate());
            });
            var errors = responseDto.getErrors();
            if (errors != null) {
                LOGGER.warning(errors.toString());
            }

            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(responseDto)
                    .build();
        }

        return null;
    }

}
