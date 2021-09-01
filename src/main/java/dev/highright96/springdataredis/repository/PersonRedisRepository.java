package dev.highright96.springdataredis.repository;

import dev.highright96.springdataredis.domain.PersonRedis;
import org.springframework.data.repository.CrudRepository;

public interface PersonRedisRepository extends CrudRepository<PersonRedis, String> {

}
