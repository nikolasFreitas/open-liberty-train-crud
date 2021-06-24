package com.crud.train.crud.repository.Entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Entity
@Table(name = "ROUTES")
@NoArgsConstructor
public class Route implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @JsonbTransient
  @Setter(value = AccessLevel.NONE)
  private Long id;

  @Column(nullable = false)
  private String destinyCity;

  @Column(nullable = false)
  private String originCity;

  @Column(nullable = false)
  private LocalDateTime departureDateTime;

  @Column(nullable = false)
  private LocalDateTime arrivelDateTime;
}
