package dev.highright96.springdataredis.domain;

import static java.lang.String.valueOf;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Getter
@RedisHash("person")
public class PersonRedis {

  @Id
  String id;
  String firstname;
  String lastname;
  String address;

  public PersonRedis(String id, String firstname, String lastname, String address) {
    this.id = id;
    this.firstname = firstname;
    this.lastname = lastname;
    this.address = address;
  }

  public static PersonRedis create(Person person) {
    return new PersonRedis(valueOf(person.getId()), person.getFirstname(), person.getLastname(),
        person.getAddress());
  }
}
