/*
	Manisha Macharla
*/

package com.book.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "cart_items")
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    //The @ManyToOne annotation signifies a many-to-one relationship between CartItem and Book.
    @ManyToOne
    //The @JoinColumn annotation specifies the name of the foreign key column in the CartItem table that links it to
    //the Book table. 
    @JoinColumn(name = "book_id")
    private Book book;
    //The @ManyToOne annotation signifies a many-to-one relationship between CartItem and User
    @ManyToOne
    //Similarly, the @JoinColumn(name = "user_id") links CartItem to the User table.
    @JoinColumn(name = "user_id")
    private User user;

    private int quantity;

    // Constructors

    public CartItem(int id, Book book, User user, int quantity) {
        super();
        this.id = id;
        this.book = book;
        this.user = user;
        this.quantity = quantity;
    }

    public CartItem() {
        super();
    }

    // Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
