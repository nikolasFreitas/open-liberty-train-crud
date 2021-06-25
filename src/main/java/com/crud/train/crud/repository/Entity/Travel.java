package com.crud.train.crud.repository.Entity;

import java.io.Serializable;
import java.util.List;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.eclipse.persistence.annotations.UuidGenerator;

import lombok.Data;

@Data
@Entity
@Table(name = "TRAVEL")
@UuidGenerator(name = "travel_uuid")
public class Travel implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(generator = "travel_uuid")
  @JsonbTransient
  private String uuid;

  @ManyToOne(fetch = FetchType.LAZY)
  private Route route;

  @ManyToMany
  private List<Passanger> passangers;

  @ManyToOne(fetch = FetchType.LAZY)
  private Train train;
}
