package de.vogella.xml.jaxb.model;

import java.util.ArrayList;

import javax.xml.bind.annotation.*;

//This statement means that class "Bookstore.java" is the root-element of our example
@XmlRootElement(namespace = "de.vogella.xml.jaxb.model")
public class Bookstore {
    private ArrayList<Book> bookList;
    private String name;
    private String location;

    public void setBookList(ArrayList<Book> bookList) {
        this.bookList = bookList;
    }

    // XmLElementWrapper generates a wrapper element around XML representation
    @XmlElementWrapper(name = "bookList")
    // XmlElement sets the name of the entities
    @XmlElement(name = "book")
    public ArrayList<Book> getBookList() {
        return bookList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
