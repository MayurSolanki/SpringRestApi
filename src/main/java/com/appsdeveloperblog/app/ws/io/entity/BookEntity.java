package com.appsdeveloperblog.app.ws.io.entity;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "book")
public class BookEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    
    @Column(nullable = false)
	private String bookId;

    private String bookName;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<BookPublisherEntity> bookPublishers = new HashSet<BookPublisherEntity>();

    public BookEntity() {
    }

//    public BookEntity(String name) {
//        this.bookName = name;
//        bookPublishers = new HashSet<>();
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public Set<BookPublisherEntity>   getBookPublishers() {
        return bookPublishers;
    }

    public void setBookPublishers(Set<BookPublisherEntity> bookPublishers) {
        this.bookPublishers = bookPublishers;
    }

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}
    
}