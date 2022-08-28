package com.summertraining.iysproject.controller;

import com.summertraining.iysproject.model.Pages;
import com.summertraining.iysproject.repo.PagesRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Service
@RequestMapping("/pages")
public class PagesController {
    private final PagesRepository pagesRepository;

    public PagesController(PagesRepository pagesRepository) {
        this.pagesRepository = pagesRepository;
    }

    @GetMapping()
    public List<Pages> getAllPages() {
        return pagesRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Pages> getPage(@PathVariable Long id) {
        return pagesRepository.findById(id);
    }

    @PostMapping()
    public Pages addPage(@RequestBody Pages pages) {
        return pagesRepository.save(pages);
    }

    @PutMapping("/{id}")
    public void updatePage(@RequestBody Pages newPage, @PathVariable Long id) {
        pagesRepository.findById(id).map(pages -> {
            pages.setName(newPage.getName());
            pages.setPackages(newPage.getPackages());

            return pagesRepository.save(pages);
        });
    }

    @DeleteMapping("/{id}")
    public void deletePage(@PathVariable Long id) {
        pagesRepository.deleteById(id);
    }
}
