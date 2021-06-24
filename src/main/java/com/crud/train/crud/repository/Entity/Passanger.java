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
@Table(name = "PASSANGERS")
@Data
@UuidGenerator(name="Passanger_uuid")
public class Passanger {

  @Id
  @GeneratedValue(generator="Passanger_uuid")
  @JsonbTransient
  private String id;

  @Column(nullable = false)
  private String name;

  @Column(unique = true, nullable = false)
  private String email;

  @ManyToMany(mappedBy = "passangers", cascade = CascadeType.ALL)
  private List<Travel> travelList = new ArrayList<>();

}
