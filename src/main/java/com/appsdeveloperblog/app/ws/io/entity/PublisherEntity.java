package com.appsdeveloperblog.app.ws.io.entity;


import javax.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity(name="publisher")
public class PublisherEntity {
	
	
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(nullable = false)
    private String publisherId;

    @Column(nullable = false)
    private String publisherName;

    @OneToMany(mappedBy = "publisher")
    private Set<BookPublisherEntity> bookPublishers = new HashSet<BookPublisherEntity>();

   

//    public PublisherEntity(String name){
////        this.publisherName = name;
////        bookPublishers = new HashSet<>();
//
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getPublisherName() {
		return publisherName;
	}

	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}

	public Set<BookPublisherEntity> getBookPublishers() {
        return bookPublishers;
    }

    public void setBookPublishers(Set<BookPublisherEntity> bookPublishers) {
        this.bookPublishers = bookPublishers;
    }

	public String getPublisherId() {
		return publisherId;
	}

	public void setPublisherId(String publisherId) {
		this.publisherId = publisherId;
	}
    
    
}


