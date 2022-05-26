package de.mainiero.spring.boot.stream.basic.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import de.mainiero.spring.boot.stream.basic.Author;
import de.mainiero.spring.boot.stream.basic.BasicAuthorRepository;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.IOException;
import java.io.OutputStream;
import java.util.function.Consumer;

@RestController
@RequestMapping("/basic/stream/authors")
public class StreamingAuthorController {

    private final BasicAuthorRepository repository;
    private final ObjectMapper mapper;

    public StreamingAuthorController(BasicAuthorRepository repository, ObjectMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @GetMapping(produces = MediaType.APPLICATION_NDJSON_VALUE)
    public ResponseEntity<StreamingResponseBody> getAllAuthors() {

        StreamingResponseBody responseBody = response -> repository
            .findAll()
            .forEach(getAuthorConsumer(response));

        return ResponseEntity
            .ok()
            .contentType(MediaType.APPLICATION_NDJSON)
            .body(responseBody);
    }

    private Consumer<Author> getAuthorConsumer(OutputStream response) {
        return entry -> {
            try {
                response.write(mapper.writeValueAsBytes(entry));
                response.write('\n'); // new line delimiter between records
                // response.flush();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };
    }
}