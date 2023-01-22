package fr.sorbonne.universite.daar_2022.model;

import jakarta.persistence.*;

@Entity
@Table(name = "book")
public class Book {

	@Column(name = "title", length = 4096)
	private String title;
	@Column(name = "author", length = 4096)
	private String author;
	@Column(name = "link", length = 4096)
	private String link;
	@Id
	@Column(name = "id", length = 4096)
	private long id;
	@Column(name = "bookshelf", length = 4096)
	private String bookshelf;

	@Column(name = "text", length = 4194304)
	private String text;

	public Book()
	{

	}

	public Book(String title, String author, String link, long id, String bookshelf, String text) {
		this.title = title;
		this.author = author;
		this.link = link;
		this.id = id;
		this.bookshelf = bookshelf;
		this.text = text;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getBookshelf() {
		return bookshelf;
	}

	public void setBookshelf(String bookshelf) {
		this.bookshelf = bookshelf;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return "Book{" +
				"title='" + title + '\'' +
				", author='" + author + '\'' +
				", link='" + link + '\'' +
				", id=" + id +
				", bookshelf='" + bookshelf + '\'' +
				'}';
	}
}
