package dev.highright96.springdataredis.domain;

import lombok.Getter;

@Getter
public class AddressRedis {

  private final String city;
  private final String country;

  public AddressRedis(String city, String country) {
    this.city = city;
    this.country = country;
  }

  public static AddressRedis of(Address address) {
    return new AddressRedis(address.getCity(), address.getCountry());
  }
}
