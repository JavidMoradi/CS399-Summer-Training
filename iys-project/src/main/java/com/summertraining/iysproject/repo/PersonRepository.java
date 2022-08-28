package com.summertraining.iysproject.repo;

import com.summertraining.iysproject.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    /**
     * This function is auto-generated
     * Thus, no need for its manual implementation
     * @return the person with specified username
     */
    Person findByUsername(String username);
}
