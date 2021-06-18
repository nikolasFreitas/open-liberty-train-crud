package com.crud.train.crud.repository.Entity;

import java.io.Serializable;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Data;

@Data
@Entity
@Table(name = "TRAIN")
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
}



