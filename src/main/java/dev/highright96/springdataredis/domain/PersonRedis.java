package dev.highright96.springdataredis.domain;

import static java.lang.String.valueOf;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RedisHash("person")
public class PersonRedis {

  public static final Long DEFAULT_TTL = 1L;

  @Id
  private String id;
  private String firstname;
  private String lastname;
  private AddressRedis address;

  @TimeToLive
  private Long expiration = DEFAULT_TTL;

  public PersonRedis(String id, String firstname, String lastname,
      AddressRedis address, Long expiration) {
    this.id = id;
    this.firstname = firstname;
    this.lastname = lastname;
    this.address = address;
    this.expiration = expiration;
  }

  public PersonRedis(String id, String firstname, String lastname,
      AddressRedis address) {
    this(id, firstname, lastname, address, DEFAULT_TTL);
  }

  public static PersonRedis createPersonExp(Person person, Long expiration) {
    return new PersonRedis(valueOf(person.getId()), person.getFirstname(), person.getLastname(),
        AddressRedis.of(person.getAddress()), expiration);
  }

  public static PersonRedis createPersonDefaultExp(Person person) {
    return new PersonRedis(valueOf(person.getId()), person.getFirstname(), person.getLastname(),
        AddressRedis.of(person.getAddress()));
  }
}
