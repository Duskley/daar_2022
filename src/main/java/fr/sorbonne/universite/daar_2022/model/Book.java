package fr.sorbonne.universite.daar_2022.model;

import javax.persistence.*;

@Entity
@Table(name = "book")
public class Book {

	@Id
	@GeneratedValue
	private long id;
	private String type;
	private String issued;
	private String title;
	private String language;
	private String authors;
	private String subjects;
	private String locc;
	private String bookshelves;

	public Book(long id, String type, String issued, String title, String language, String authors, String subjects, String locc, String bookshelves) {
		this.id = id;
		this.type = type;
		this.issued = issued;
		this.title = title;
		this.language = language;
		this.authors = authors;
		this.subjects = subjects;
		this.locc = locc;
		this.bookshelves = bookshelves;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getIssued() {
		return issued;
	}

	public void setIssued(String issued) {
		this.issued = issued;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getAuthors() {
		return authors;
	}

	public void setAuthors(String authors) {
		this.authors = authors;
	}

	public String getSubjects() {
		return subjects;
	}

	public void setSubjects(String subjects) {
		this.subjects = subjects;
	}

	public String getLocc() {
		return locc;
	}

	public void setLocc(String locc) {
		this.locc = locc;
	}

	public String getBookshelves() {
		return bookshelves;
	}

	public void setBookshelves(String bookshelves) {
		this.bookshelves = bookshelves;
	}

	@Override
	public String toString() {
		return "Book{" +
				"id=" + id +
				", type='" + type + '\'' +
				", issued='" + issued + '\'' +
				", title='" + title + '\'' +
				", language='" + language + '\'' +
				", authors='" + authors + '\'' +
				", subjects='" + subjects + '\'' +
				", locc='" + locc + '\'' +
				", bookshelves='" + bookshelves + '\'' +
				'}';
	}
}
