package fr.sorbonne.universite.daar_2022.repository;

import fr.sorbonne.universite.daar_2022.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

}