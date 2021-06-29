package com.crud.train.crud.repository.Entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.eclipse.persistence.annotations.UuidGenerator;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "TRAVEL")
@UuidGenerator(name = "travel_uuid")
@NoArgsConstructor
public class Travel implements Serializable {
  private static final long serialVersionUID = 1L;

  public Travel(Route route, Train train, LocalDateTime departureDateTime, LocalDateTime arrivelDateTime) {
    this.route = route;
    this.train = train;
    this.departureDateTime = departureDateTime;
    this.arrivelDateTime = arrivelDateTime;
  }

  @Id
  @GeneratedValue(generator = "travel_uuid")
  @JsonbTransient
  private String uuid;

  @ManyToOne(fetch = FetchType.LAZY)
  private Route route;

  @ManyToMany
  private List<Passenger> passengers;

  @ManyToOne(fetch = FetchType.LAZY)
  private Train train;

  @Column(nullable = false)
  private LocalDateTime departureDateTime;

  @Column(nullable = false)
  private LocalDateTime arrivelDateTime;
}
