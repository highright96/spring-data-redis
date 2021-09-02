package dev.highright96.springdataredis.controller;

import dev.highright96.springdataredis.aop.Timer;
import dev.highright96.springdataredis.domain.Address;
import dev.highright96.springdataredis.domain.Person;
import dev.highright96.springdataredis.domain.PersonRedis;
import dev.highright96.springdataredis.repository.PersonRedisRepository;
import dev.highright96.springdataredis.repository.PersonRepository;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/person")
public class PersonController {

  private final PersonRepository personRepository;
  private final PersonRedisRepository personRedisRepository;

  //@PostConstruct
  public ResponseEntity<Void> createPerson() {
    List<Person> personList = new ArrayList<>();
    List<PersonRedis> personRedisList = new ArrayList<>();
    for (int i = 1; i <= 100000; i++) {
      Address address = new Address("서울특별시", "대한민국");
      Person person = new Person(null, "상우 " + i, "남", address);
      personList.add(person);
    }
    // DB 에 저장
    List<Person> savedPersonList = personRepository.saveAll(personList);
    // cache 에 저장
    for (Person person : savedPersonList) {
      personRedisList.add(PersonRedis.createPersonExp(person, 300L));
    }
    personRedisRepository.saveAll(personRedisList);
    return ResponseEntity.ok().build();
  }

  @Timer
  @GetMapping("/redis/{id}")
  public ResponseEntity<PersonRedis> findPersonByRedis(@PathVariable String id) {
    PersonRedis personRedis = personRedisRepository.findById(id).get();
    return ResponseEntity.ok(personRedis);
  }

  @Timer
  @GetMapping("/database/{id}")
  public ResponseEntity<Person> findPersonByDatabase(@PathVariable Long id) {
    Person person = personRepository.findById(id).get();
    return ResponseEntity.ok(person);
  }
}
