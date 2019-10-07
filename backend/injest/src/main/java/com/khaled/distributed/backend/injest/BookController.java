package com.khaled.distributed.backend.injest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

/**
 * Defines the Books Controller
 */
@RestController
public class BookController {

    @Autowired
    BookRepository bookRepository;



    @GetMapping(path="/createBooks")
    public String getBooks() {
        bookRepository.save(new Book("X1"));
        bookRepository.save(new Book("X2"));
        bookRepository.save(new Book("X3"));
        return "Books are created";
    }


    @GetMapping(path="/deleteAll")
    public String deleteBooks() {
        bookRepository.deleteAll();
        return "Books are deleted";
    }


    @GetMapping("/findall")
    public Iterable<Book> findAll(){
        return bookRepository.findAll();
    }

    @GetMapping("/findById/{id}")
    public Optional<Book> findById(@PathVariable long id){
        Optional<Book> book = bookRepository.findById(id);
        if(book.isEmpty())  {
            throw new BookNotFoundException("id-" + id);
        }
        return book;

    }

    @GetMapping("/findByName/{name}")
    public List<Book> findByName(@PathVariable String name){
        return bookRepository.findByName(name);
    }

    @PostMapping("/book")
    public ResponseEntity<Object> createBook(@RequestBody Book book) {
        Book savedBook = bookRepository.save(book);

        // To return the location of the created book inside the header response
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedBook.getId()).toUri();

        return ResponseEntity.created(location).build();

    }



}
