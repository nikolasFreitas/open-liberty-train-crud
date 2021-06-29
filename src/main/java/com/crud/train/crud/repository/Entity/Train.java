package com.crud.train.crud.repository.Entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.eclipse.persistence.annotations.UuidGenerator;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data
@Entity
@Table(name = "TRAIN")
@UuidGenerator(name="Passanger_uuid")
public class Train implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @JsonbTransient
  @Setter(value = AccessLevel.NONE)
  private Long id;

  @Column(name = "LOCOMOTIVE_MODEL", nullable = false)
  private String locomotiveModel;

  @Column(name = "WAGONS_QUANTITY", nullable = false)
  private Integer qtdWagons;

  @OneToMany(mappedBy = "train", cascade = CascadeType.ALL)
  private List<Travel> travelList = new ArrayList<>();

  public void addTravel(Travel travel) {
    travel.setTrain(this);
    travelList.add(travel);
  }
}



