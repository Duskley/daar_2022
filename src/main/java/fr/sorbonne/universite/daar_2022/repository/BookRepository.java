package fr.sorbonne.universite.daar_2022.repository;

import fr.sorbonne.universite.daar_2022.model.Book;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends PagingAndSortingRepository<Book, Long> {

    Book findById(Long id);
    List<Book> findByTextContaining(String query, Pageable pageable);
    @Query(value="SELECT * FROM book WHERE text ~ :regex",
           countQuery = "SELECT count(*) FROM book WHERE text ~ :regex",
           nativeQuery = true)
    List<Book> findTextWithRegex(@Param("regex") String regex, Pageable pageable);

    @Query(value = "SELECT * FROM book",
            countQuery  = "SELECT *, (LENGTH(text) - LENGTH(REPLACE(text, :word, ''))) / LENGTH(:word) AS OccurrenceCount FROM BOOK GROUP BY id ORDER BY OccurrenceCount DESC LIMIT 5",
            nativeQuery = true)
    List<Book> findByWordScore(String word, Pageable pageable);
}