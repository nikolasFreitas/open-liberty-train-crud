package com.crud.train.crud.repository.Entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.eclipse.persistence.annotations.UuidGenerator;

import lombok.Data;

@Entity
@Table(name = "PASSENGERS")
@Data
@UuidGenerator(name="Passenger_uuid")
public class Passenger {

  @Id
  @GeneratedValue(generator="Passenger_uuid")
  private String uuid;

  @Column(nullable = false)
  private String name;

  @Column(unique = true, nullable = false)
  private String email;

  @ManyToMany
  @JoinTable(name = "TRAVEL_PASSENGERS",
    joinColumns = {@JoinColumn(name = "travel_id")},
    inverseJoinColumns = @JoinColumn(name = "passenger_uuid")
  )
  private List<Travel> travelList = new ArrayList<>();

  public void addTravel(Travel travel) {
    travel.getPassengers().add(this);
    travelList.add(travel);
  }
}
