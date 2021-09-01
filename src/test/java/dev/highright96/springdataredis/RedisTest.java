package dev.highright96.springdataredis;

import static org.assertj.core.api.Assertions.assertThat;

import dev.highright96.springdataredis.domain.PersonRedis;
import dev.highright96.springdataredis.repository.PersonRedisRepository;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RedisTest {

  @Autowired
  PersonRedisRepository personRedisRepository;

  @Test
  void basicCrudTest() {
    PersonRedis person = new PersonRedis("highright96", "first", "last", "서울특별시 강동구");

    PersonRedis savedPerson = personRedisRepository.save(person);
    Optional<PersonRedis> findPerson = personRedisRepository.findById(savedPerson.getId());

    assertThat(findPerson.isPresent()).isEqualTo(true);
    assertThat(findPerson.get().getFirstname()).isEqualTo(person.getFirstname());
  }
}
