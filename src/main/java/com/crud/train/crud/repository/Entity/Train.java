package com.crud.train.crud.repository.Entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "TRAIN")
@NoArgsConstructor
public class Train implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @JsonbTransient
  private Long id;

  @NotEmpty(message = "Model must no be empty")
  @Column(name = "LOCOMOTIVE_MODEL", nullable = false)
  private String locomotiveModel;

  @NotNull(message = "Quantity of wagons must no be NULL")
  @Column(name = "WAGONS_QUANTITY", nullable = false)
  private Integer qtdWagons;

  @OneToMany(mappedBy = "train")
  private List<Travel> travel = new ArrayList<>();

}



