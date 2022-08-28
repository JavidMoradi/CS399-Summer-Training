package com.summertraining.iysproject.repo;

import com.summertraining.iysproject.model.Employees;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeesRepository extends JpaRepository<Employees, Long> {
}
