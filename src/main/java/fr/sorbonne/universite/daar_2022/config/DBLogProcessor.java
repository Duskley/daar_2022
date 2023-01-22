package fr.sorbonne.universite.daar_2022.config;

import fr.sorbonne.universite.daar_2022.model.Book;
import org.springframework.batch.item.ItemProcessor;

public class DBLogProcessor implements ItemProcessor<Book, Book>
{
    public Book process(Book book) throws Exception
    {
        System.out.println("Inserting Book : " + book.toString());
        return book;
    }
}
