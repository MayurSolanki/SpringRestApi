package com.appsdeveloperblog.app.ws.io.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "book_publisher")
public class BookPublisherEntity implements Serializable{
    
	/**
	 * 
	 */
	private static final long serialVersionUID = 2293375196095422579L;


	@Id
    @ManyToOne
    @JoinColumn(name = "book_id")       // Table name _ primary column name 
    private BookEntity book;

    
    @Id
    @ManyToOne
    @JoinColumn(name = "publisher_id")   // Table name _ primary column name 
    private PublisherEntity publisher;
    

    @Column(name = "published_date")
    private Date publishedDate;

    public BookEntity getBook() {
        return book;
    }

    public void setBook(BookEntity book) {
        this.book = book;
    }


    public PublisherEntity getPublisher() {
        return publisher;
    }

    public void setPublisher(PublisherEntity publisher) {
        this.publisher = publisher;
    }

    public Date getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(Date publishedDate) {
        this.publishedDate = publishedDate;
    }
}
