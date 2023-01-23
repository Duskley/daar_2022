package fr.sorbonne.universite.daar_2022.controller;

import fr.sorbonne.universite.daar_2022.model.Book;
import fr.sorbonne.universite.daar_2022.services.BasicSearchService;
import fr.sorbonne.universite.daar_2022.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin("http://localhost:8081")
@Controller
@RequestMapping("/basic-search")
public class BasicSearchController {

    @Autowired
    BasicSearchService basicSearchService;

    @GetMapping
    public ResponseEntity<List<Book>> basicSearch(@RequestParam String query, @RequestParam int page) throws URISyntaxException {

        try {
            List<Book> books = basicSearchService.findBooksByQuery(query,page);

            if (books.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(books, HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
