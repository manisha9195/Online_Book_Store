/*
	Manisha Macharla and kirtana Kuntla
*/
package com.book.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.book.entity.Book;
import com.book.repository.BookRepository;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    // This method is used to save a book entity. It receives a Book object as a parameter
    // and uses the bookRepository to save it to the database.
    public void save(Book b) {
        bookRepository.save(b);
    }

    // This method retrieves all books from the database using the findAll() method
    // provided by the bookRepository and returns a List of Book entities.
    public List<Book> getAllBook() {
        return bookRepository.findAll();
    }

    // This method retrieves a book by its ID using the findById() method provided by the bookRepository.
    // It returns the Book entity associated with the provided ID.
    public Book getBookById(int id) {
        return bookRepository.findById(id).get();
    }

    // This method deletes a book by its ID using the deleteById() method provided by the bookRepository.
    public void deleteById(int id) {
        bookRepository.deleteById(id);
    }
}
