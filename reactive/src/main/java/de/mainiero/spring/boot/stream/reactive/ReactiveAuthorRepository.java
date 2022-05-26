package de.mainiero.spring.boot.stream.reactive;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ReactiveAuthorRepository extends ReactiveCrudRepository<Author, Long> {
}