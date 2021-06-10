package com.crud.train.crud.repository.Entity;

import java.io.Serializable;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
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

  @TableGenerator(name = "id_generator", table = "ID_GEN", pkColumnName = "gen_name", valueColumnName = "gen_value",
  pkColumnValue="user_gen", initialValue=1000, allocationSize=10)
  @Id
  @GeneratedValue(strategy = GenerationType.TABLE, generator = "id_generator")
  @JsonbTransient
  private Long id;

  @NotEmpty(message = "Model must no be empty")
  @Column(name = "LOCOMOTIVE_MODEL", nullable = false)
  private String locomotiveModel;

  @NotNull(message = "Quantity of wagons must no be NULL")
  @Column(name = "WAGONS_QUANTITY", nullable = false)
  private Integer qtdWagons;
}



