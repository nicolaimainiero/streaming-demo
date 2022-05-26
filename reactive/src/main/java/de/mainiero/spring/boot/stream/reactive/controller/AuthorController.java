package de.mainiero.spring.boot.stream.reactive.controller;


import de.mainiero.spring.boot.stream.reactive.Author;
import de.mainiero.spring.boot.stream.reactive.ReactiveAuthorRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/reactive/rest/authors")
public class AuthorController {

    private final ReactiveAuthorRepository repository;

    public AuthorController(ReactiveAuthorRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public Flux<Author> getAllAuthors() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Mono<Author> getAuthor(@PathVariable("id") Long id) {
        return repository.findById(id);
    }
}