package com.summertraining.iysproject.controller;

import com.summertraining.iysproject.model.Methods;
import com.summertraining.iysproject.repo.MethodsRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;
import java.util.Optional;

@RestController
@Service
@RequestMapping("/methods")
public class MethodsController {
    private final MethodsRepository methodsRepository;

    public MethodsController(MethodsRepository methodsRepository) {
        this.methodsRepository = methodsRepository;
    }

    @GetMapping()
    public List<Methods> getAllMethods() {
        return methodsRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Methods> getMethod(@PathVariable Long id) {
        return methodsRepository.findById(id);
    }

    @PostMapping()
    public Methods addMethod(@RequestBody Methods methods) {
        return methodsRepository.save(methods);
    }

    @PutMapping("/{id}")
    public void updateMethod(@RequestBody Methods newMethods, @PathVariable Long id) {
        methodsRepository.findById(id).map(methods -> {
            methods.setName(newMethods.getName());
            methods.setFunctionName(newMethods.getFunctionName());

            return methodsRepository.save(methods);
        });
    }

    @DeleteMapping("/{id}")
    public void deleteMethod(@PathVariable Long id) {
        methodsRepository.deleteById(id);
    }
}
