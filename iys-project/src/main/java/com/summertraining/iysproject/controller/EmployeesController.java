package com.summertraining.iysproject.controller;

import com.summertraining.iysproject.model.Employees;
import com.summertraining.iysproject.repo.EmployeesRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employees")
public class EmployeesController {
    private final EmployeesRepository employeesRepository;

    public EmployeesController(EmployeesRepository employeesRepository) {
        this.employeesRepository = employeesRepository;
    }

    @GetMapping()
    public List<Employees> getAllEmployees() {
        return employeesRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Employees> getEmployee(@PathVariable Long id) {
        return employeesRepository.findById(id);
    }

    @PostMapping()
    public void addEmployee(@RequestBody Employees employees) {
        employeesRepository.save(employees);
    }

    @PutMapping("/{id}")
    public void updateEmployee(@RequestBody Employees newEmployees, @PathVariable Long id) {
        employeesRepository.findById(id).map(employees -> {
            employees.setName(newEmployees.getName());
            employees.setSurname(newEmployees.getSurname());
            employees.setAge(newEmployees.getAge());

            return employeesRepository.save(employees);
        });
    }

    @DeleteMapping("/{id}")
    public void removeEmployee(@PathVariable Long id) {
        employeesRepository.deleteById(id);
    }
}
