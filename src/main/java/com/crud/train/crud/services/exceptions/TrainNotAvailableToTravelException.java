package com.crud.train.crud.services.exceptions;

import javax.ejb.ApplicationException;
import java.io.Serializable;
import java.time.LocalDateTime;

@ApplicationException
public class TrainNotAvailableToTravelException extends RuntimeException implements Serializable {
  private static final long serialVersionUID = 1L;
  private static final String MESSAGE = "Train not available to travel";

  public TrainNotAvailableToTravelException() {
      super(MESSAGE);
  }

  public TrainNotAvailableToTravelException(LocalDateTime departure) {
    super(createTravelWithDepartureMessage(departure));

  }

  private static String createTravelWithDepartureMessage(LocalDateTime departure) {
    return new StringBuilder(MESSAGE)
              .append(" ")
              .append("at")
              .append(" ")
              .append(departure.toString())
              .toString();
  }
}
