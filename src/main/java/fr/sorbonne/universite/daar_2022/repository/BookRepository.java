package fr.sorbonne.universite.daar_2022.repository;

import fr.sorbonne.universite.daar_2022.model.Book;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface BookRepository extends PagingAndSortingRepository<Book, Long> {

    Book findById(Long id);
    List<Book> findByTextContaining(String infix, Pageable pageable);

}