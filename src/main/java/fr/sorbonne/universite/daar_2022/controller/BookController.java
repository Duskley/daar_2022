package fr.sorbonne.universite.daar_2022.controller;

import fr.sorbonne.universite.daar_2022.model.Book;
import fr.sorbonne.universite.daar_2022.repository.BookRepository;
import fr.sorbonne.universite.daar_2022.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@CrossOrigin("http://localhost:8081")
@Controller
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookService bookService;

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        try {
            List<Book> books = bookService.getAllBooks();

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

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Book>> getBookById(@PathVariable Long id) {
        try {
            Optional<Book> book = bookService.getBookById(id);

            if (Objects.isNull(book)) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(book, HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity createBook(@RequestBody Book book) throws URISyntaxException {
        Book savedBook = bookService.createBook(book);
        return ResponseEntity.created(new URI("/book/" + savedBook.getId())).body(savedBook);
    }

    /*
    @PutMapping("/{id}")
    public ResponseEntity updateClient(@PathVariable Long id, @RequestBody Book book) {
        Book currentBook = BookRepository.findById(id).orElseThrow(RuntimeException::new);
        currentBook.setName(book.getName());
        currentBook.setEmail(book.getEmail());
        currentBook = bookRepository.save(book);

        return ResponseEntity.ok(currentBook);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteClient(@PathVariable Long id) {
        BookRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
    */
}
