package Models;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.*;

@RestController
public class PersonController {
    private final PersonRepository repository;

    public PersonController(PersonRepository repository) {
        this.repository = repository;
    }

    @GetMapping("People")
    List<Person> getAll() {
        return repository.findAll();
    }

    @PostMapping("People")
    Person addPerson(@RequestBody Person newPerson) {
        return repository.save(newPerson);
    }

    @GetMapping("People/{id}")
    Person getPerson(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new PersonNotFoundException(id));
    }

    @PutMapping("People/{id}")
    Optional<Person> updatePerson(@RequestBody Person newPerson, @PathVariable Long id) {
        return repository.findById(id).map(person -> {
            person.setName(newPerson.getName());
            person.setAge(newPerson.getAge());
            person.setTitle(newPerson.getTitle());
            return repository.save(person);
        });
    }

    @DeleteMapping("People/{id}")
    void deletePerson(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
