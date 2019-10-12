package com.khaled.injest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

/**
 * Defines the Books Controller
 */
@RestController
@CrossOrigin(origins="*")
public class BookController {
    @Autowired
    BookRepository bookRepository;
    /**
     *
     * @param book this is a JSON object that looks like this. {"name": "tryMe"}
     * @return
     */

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

    @GetMapping(path="/pan")
    public String pan() throws IOException, InterruptedException {
        ProcessBuilder builder = new ProcessBuilder(
                "cmd.exe", "/c", "pandoc readthat.txt -t html -s -o writethat.html");
        builder.redirectErrorStream(true);
        Process p = builder.start();
        BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;
        while (true) {
            line = r.readLine();
            if (line == null) { break; }
            System.out.println(line);
        }
        return Paths.get("").toAbsolutePath().toString();
    }



    @GetMapping(path="/mypath")
    public String path() throws IOException {
        // String content = new String(Files.readAllBytes(Paths.get("./readthis.html")));

        return Paths.get("").toAbsolutePath().toString();
    }

    @GetMapping(path="/read")
    public String _() throws IOException {
        String content = new String(Files.readAllBytes(Paths.get("./readthis.html")));
        bookRepository.save(new Book(content.substring(0,10),content));
        return content.substring(0,10);
    }


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
        if(!book.isPresent())  {
            throw new BookNotFoundException("id-" + id);
        }
        return book;

    }

    @GetMapping("/findContentById/{id}")
    public String findContentById(@PathVariable long id){
        Optional<Book> book = bookRepository.findById(id);
        if(!book.isPresent())  {
            throw new BookNotFoundException("id-" + id);
        }
        return book.get().getContent();

    }

    @GetMapping("/findByName/{name}")
    public List<Book> findByName(@PathVariable String name){
        return bookRepository.findByName(name);
    }





}
