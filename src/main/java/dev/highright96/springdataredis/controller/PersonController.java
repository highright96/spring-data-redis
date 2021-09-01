package dev.highright96.springdataredis.controller;

import dev.highright96.springdataredis.aop.Timer;
import dev.highright96.springdataredis.domain.Person;
import dev.highright96.springdataredis.domain.PersonRedis;
import dev.highright96.springdataredis.repository.PersonRedisRepository;
import dev.highright96.springdataredis.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/person")
public class PersonController {

  private final PersonRepository personRepository;
  private final PersonRedisRepository personRedisRepository;

  @PostMapping
  public ResponseEntity<Long> createPerson() {
    Person person = new Person(null, "상우", "남", "서울특별시 강동구");
    // DB 에 저장
    Person savedPerson = personRepository.save(person);
    // cache 에 저장
    personRedisRepository.save(PersonRedis.create(savedPerson));
    return ResponseEntity.ok(savedPerson.getId());
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
