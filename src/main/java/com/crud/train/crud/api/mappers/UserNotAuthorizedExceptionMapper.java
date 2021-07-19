package com.crud.train.crud.api.mappers;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.crud.train.crud.services.exceptions.UserNotAuthorizedException;

@Provider
public class UserNotAuthorizedExceptionMapper implements ExceptionMapper<UserNotAuthorizedException> {

  @Override
  public Response toResponse(UserNotAuthorizedException exception) {
    
    return Response.status(Response.Status.UNAUTHORIZED).build();
  }    
}