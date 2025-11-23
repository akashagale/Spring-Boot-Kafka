package com.consumer.service;

import com.consumer.dto.LibraryEvent;
import com.consumer.repo.LibraryEventRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class LibraryEventsService {

    private ObjectMapper objectMapper;

    private LibraryEventRepository libraryEventRepo;


    public void processLibraryEvents(ConsumerRecord<Integer,String> consumerRecord) throws JsonProcessingException {
        LibraryEvent libraryEvent = objectMapper.readValue(consumerRecord.value(), LibraryEvent.class);
        log.info("libraryEvent : {} ", libraryEvent);

        switch (libraryEvent.getLibraryEventType()){
            case NEW:
                // save operation
                save(libraryEvent);
                break;
            case UPDATE:
                // validate the library event
                validate(libraryEvent);

                // save
                save(libraryEvent);

                break;
            default:
                log.info("Invalid Library Event Type");
        }
    }

    private void validate(LibraryEvent libraryEvent) {
        if (libraryEvent.getLibraryEventId() == null){
            throw new IllegalArgumentException("Library Event id is missing");
        }
        Optional<LibraryEvent> isAvailable = libraryEventRepo.findById((libraryEvent.getLibraryEventId()));
        if (! isAvailable.isPresent()){
            throw new IllegalArgumentException("Not a valid Library Event");
        }
        log.info("Validation is Successful for the Library Event : {}",Optional.of(isAvailable));

    }

    private void save(LibraryEvent libraryEvent) {
        libraryEvent.getBook().setLibraryEvent(libraryEvent);
        this.libraryEventRepo.save(libraryEvent);
        log.info("Successfully Persisted the library Event : {} ",libraryEvent);
    }
}
