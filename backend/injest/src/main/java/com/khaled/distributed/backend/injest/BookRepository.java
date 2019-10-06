package com.khaled.distributed.backend.injest;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, Long> {

    /**
     * 🔮 This is magical method because  Spring Knows that you want to findBy Name from the
     * method name !
     * So if you have findByWhatever, spring will try to find Whatever for you.
     * @param x it can be named anything
     * @return A single book
     */
    List<Book> findByName(String x);

}