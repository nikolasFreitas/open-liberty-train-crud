package com.crud.train.crud.api.mappers;


import com.crud.train.crud.services.exceptions.UserForbiddenException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class UserForbiddenExceptionMapper implements ExceptionMapper<UserForbiddenException> {

    @Override
    public Response toResponse(UserForbiddenException exception) {
        return Response.status(Response.Status.FORBIDDEN).build();
    }
}
