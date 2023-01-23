package fr.sorbonne.universite.daar_2022.services;

import fr.sorbonne.universite.daar_2022.model.Book;
import fr.sorbonne.universite.daar_2022.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BasicSearchService {

    @Autowired
    BookRepository bookRepository;

    public List<Book> findBooksByQuery(String query, int page)
    {
        Pageable pageable = PageRequest.of(page,10);
        return bookRepository.findByTextContaining(query, pageable);
    }
}
