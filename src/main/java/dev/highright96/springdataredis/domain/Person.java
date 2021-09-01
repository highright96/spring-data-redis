package dev.highright96.springdataredis.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Person {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;
  String firstname;
  String lastname;
  String address;

  public Person(Long id, String firstname, String lastname, String address) {
    this.id = id;
    this.firstname = firstname;
    this.lastname = lastname;
    this.address = address;
  }
}
