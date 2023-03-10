package fr.sorbonne.universite.daar_2022.services;

import fr.sorbonne.universite.daar_2022.model.Book;
import fr.sorbonne.universite.daar_2022.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;

    public Page<Book> getAllBooks(int page) {
        Pageable pageable = PageRequest.of(page,10);
        return bookRepository.findAll(pageable);
    }

    public Optional<Book> getBookById(Long id) {
        return Optional.ofNullable(bookRepository.findById(id));
    }
}
