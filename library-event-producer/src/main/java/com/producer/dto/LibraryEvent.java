package com.producer.dto;

public record LibraryEvent(
        Integer libraryEventId,

        LibraryEventType libraryEventType,

        Book book

) {
}
