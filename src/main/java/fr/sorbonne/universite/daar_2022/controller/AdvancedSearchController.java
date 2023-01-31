package fr.sorbonne.universite.daar_2022.controller;

import fr.sorbonne.universite.daar_2022.model.Book;
import fr.sorbonne.universite.daar_2022.services.AdvancedSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URISyntaxException;
import java.util.List;

@CrossOrigin("http://localhost:8081")
@Controller
@RequestMapping("/advanced-search")
public class AdvancedSearchController {

    @Autowired
    AdvancedSearchService advancedSearchService;

    @GetMapping
    public ResponseEntity<List<Book>> advancedSearch(@RequestParam String regex, @RequestParam int page) throws URISyntaxException {
        
        try {
            List<Book> books = advancedSearchService.findBooksByRegex(regex,page);

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
