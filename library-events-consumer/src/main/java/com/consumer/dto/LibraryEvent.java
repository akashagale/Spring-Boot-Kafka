package com.consumer.dto;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LibraryEvent{

        @Id
        @GeneratedValue
        private Integer libraryEventId;

        @Enumerated(EnumType.STRING)
        private LibraryEventType libraryEventType;

        @OneToOne(mappedBy = "libraryEvent",cascade = {CascadeType.ALL})
        @ToString.Exclude
        private Book book;
}