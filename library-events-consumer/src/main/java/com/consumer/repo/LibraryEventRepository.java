package com.consumer.repo;

import com.consumer.dto.LibraryEvent;
import org.springframework.data.repository.CrudRepository;

public interface LibraryEventRepository extends CrudRepository<LibraryEvent,Integer> {
}
