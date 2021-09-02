package dev.highright96.springdataredis;

import static org.assertj.core.api.Assertions.assertThat;

import dev.highright96.springdataredis.domain.AddressRedis;
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
    AddressRedis addressRedis = new AddressRedis("서울특별시", "대한민국");
    PersonRedis person = new PersonRedis("highright96", "상우", "남", addressRedis, 300L);

    PersonRedis savedPerson = personRedisRepository.save(person);

    Optional<PersonRedis> findPerson = personRedisRepository.findById(savedPerson.getId());

    assertThat(findPerson.isPresent()).isEqualTo(true);
    assertThat(findPerson.get().getFirstname()).isEqualTo(person.getFirstname());
    assertThat(findPerson.get().getAddress().getCity()).isEqualTo(person.getAddress().getCity());
  }

  @Test
  void TimeToLiveTest() throws InterruptedException {
    AddressRedis addressRedis = new AddressRedis("서울특별시", "대한민국");
    PersonRedis person = new PersonRedis("highright96", "상우", "남", addressRedis);

    PersonRedis savedPerson = personRedisRepository.save(person);

    Thread.sleep(1000);

    Optional<PersonRedis> findPerson = personRedisRepository.findById(savedPerson.getId());

    assertThat(findPerson.isPresent()).isEqualTo(false);
  }
}
