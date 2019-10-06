package com.khaled.distributed.backend.injest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Defines the Books Controller
 */
@RestController
public class BookController {

    @GetMapping(path="/Books")
    public String getBooks() {
        return "some";
    }


}
