package com.crud.train.crud.repository.Entity;

import java.util.ArrayList;
import java.util.List;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
  @JsonbTransient
  private String uuid;

  @Column(nullable = false)
  private String name;

  @Column(unique = true, nullable = false)
  private String email;

  @ManyToMany(mappedBy = "passengers", cascade = CascadeType.ALL)
  private List<Travel> travelList = new ArrayList<>();

}
