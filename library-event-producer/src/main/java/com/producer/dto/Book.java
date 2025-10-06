package com.producer.dto;

public record Book(
        Integer bookId,
        String bookName,
        String bookAuthor
) {
}
