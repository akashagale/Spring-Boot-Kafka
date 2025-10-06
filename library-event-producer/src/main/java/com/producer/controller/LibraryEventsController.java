package com.producer.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.producer.dto.LibraryEvent;
import com.producer.producer.LibraryEventsProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

@RestController
@Slf4j
public class LibraryEventsController {

    private final LibraryEventsProducer producer;

    public LibraryEventsController(LibraryEventsProducer producer) {
        this.producer = producer;
    }

    @PostMapping("/v1/libraryevent")
    public ResponseEntity<LibraryEvent> postLibraryEvent(@RequestBody LibraryEvent libraryEvent) throws JsonProcessingException, ExecutionException, InterruptedException, TimeoutException {
        // invoke the kafka producer
        log.info("LibraryEvent : {}", libraryEvent);
        System.out.println("<----------------LibraryEvent : {}---------------->"+libraryEvent);
        producer.sendLibraryEvents_approach3(libraryEvent);
        System.out.println("<----------------After sending LibraryEvent :---------------->");
        return ResponseEntity.status(HttpStatus.CREATED).body(libraryEvent);
    }
}
