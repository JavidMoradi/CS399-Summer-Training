package com.summertraining.iysproject.repo;

import com.summertraining.iysproject.model.Methods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MethodsRepository extends JpaRepository<Methods, Long> {
}
