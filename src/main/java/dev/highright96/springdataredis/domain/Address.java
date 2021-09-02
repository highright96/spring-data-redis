package dev.highright96.springdataredis.domain;

import javax.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@NoArgsConstructor
public class Address {

  private String city;
  private String country;

  public Address(String city, String country) {
    this.city = city;
    this.country = country;
  }
}
