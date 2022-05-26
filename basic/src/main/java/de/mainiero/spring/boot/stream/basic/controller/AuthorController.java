package de.mainiero.spring.boot.stream.basic.controller;


import de.mainiero.spring.boot.stream.basic.Author;
import de.mainiero.spring.boot.stream.basic.BasicAuthorRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/basic/rest/authors")
public class AuthorController {

    private final BasicAuthorRepository repository;

    public AuthorController(BasicAuthorRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public Iterable<Author> getAllAuthors() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Author> getAuthor(@PathVariable("id") Long id) {
        return repository.findById(id);
    }
}