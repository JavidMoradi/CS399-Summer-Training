package com.summertraining.iysproject.controller;

import com.summertraining.iysproject.model.Person;
import com.summertraining.iysproject.repo.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@Service
@RequiredArgsConstructor
@RequestMapping("/person")
public class PersonController implements UserDetailsService {
    private final PersonRepository personRepository;
    private final PasswordEncoder passwordEncoder;

    @GetMapping()
    public List<Person> getAllPerson() {
        return personRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Person> getPerson(@PathVariable Long id) {
        return personRepository.findById(id);
    }

    @GetMapping("/user/{username}")
    public Person getPerson(@PathVariable String username) {
        return personRepository.findByUsername(username);
    }

    @PostMapping()
    public Person addPerson(@RequestBody Person person) {
        person.setPassword(passwordEncoder.encode(person.getPassword()));

        return personRepository.save(person);
    }

    @PutMapping("/{id}")
    public void updatePerson(@RequestBody Person newPerson, @PathVariable Long id) {
        personRepository.findById(id).map(person -> {
            person.setPassword(newPerson.getPassword());
            person.setUsername(newPerson.getUsername());

            return personRepository.save(person);
        });
    }

    @DeleteMapping("/{id}")
    public void deletePerson(@PathVariable Long id) {
        personRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person user = personRepository.findByUsername(username);

        if (user == null) {
            System.out.println("Given user with username not found.");
            throw new UsernameNotFoundException("User not found.");
        }

        // the auths of a user is neglected
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), new ArrayList<>());
    }
}
