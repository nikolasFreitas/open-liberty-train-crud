package com.crud.train.crud.repository.Entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.eclipse.persistence.annotations.UuidGenerator;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TRAVEL")
@UuidGenerator(name = "travel_uuid")
@Data
@NoArgsConstructor
public class Travel {
  public Travel(Route route, Train train, LocalDateTime departureDateTime, LocalDateTime arrivelDateTime) {
    this.route = route;
    this.train = train;
    this.departureDateTime = departureDateTime;
    this.arrivelDateTime = arrivelDateTime;
  }

  @Id
  @GeneratedValue(generator = "travel_uuid")
  private String uuid;

  @ManyToOne
  private Route route;

  @ManyToMany(mappedBy = "travelList")
  private List<Passenger> passengers = new ArrayList<>();

  @ManyToOne
  private Train train;

  @Column(nullable = false)
  private LocalDateTime departureDateTime;

  @Column(nullable = false)
  private LocalDateTime arrivelDateTime;

  public void addTravel(Passenger passenger) {
    passenger.getTravelList().add(this);
    passengers.add(passenger);
  }
}
