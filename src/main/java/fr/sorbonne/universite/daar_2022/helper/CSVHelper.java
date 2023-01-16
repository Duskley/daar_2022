package fr.sorbonne.universite.daar_2022.helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import fr.sorbonne.universite.daar_2022.model.Book;

public class CSVHelper {
    public static String TYPE = "text/csv";

    public static boolean hasCSVFormat(MultipartFile file)
    {
        if (!TYPE.equals(file.getContentType())) {
            return false;
        }
        return true;
    }

    public static List<Book> csvToBooks(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

            List<Book> books = new ArrayList<Book>();
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                Book book = new Book(
                        Long.parseLong(csvRecord.get("Id")),
                        csvRecord.get("type"),
                        csvRecord.get("issued"),
                        csvRecord.get("title"),
                        csvRecord.get("language"),
                        csvRecord.get("authors"),
                        csvRecord.get("subjects"),
                        csvRecord.get("locc"),
                        csvRecord.get("bookshelves")
                );
                books.add(book);
            }
            return books;
        }
        catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }
}