package dev.highright96.springdataredis.repository;

import dev.highright96.springdataredis.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {

}
