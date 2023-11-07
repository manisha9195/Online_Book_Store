/*
	Manisha Macharla
*/

package com.book.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.book.service.BookService;
import com.book.entity.Book;

@Controller
public class BookController {

    @Autowired
    BookService bookService;

    @GetMapping("/admin_book_register")
    public String bookRegister() {
        // This method handles GET requests for the "/admin_book_register" URL and returns the "adminBookRegister" view.
        return "adminBookRegister";
    }

    @GetMapping("/available_books")
    public ModelAndView getAllBook() {
        // This method handles GET requests for the "/available_books" URL.
        // It retrieves a list of all books using the BookService and renders the "bookList" view.
        List<Book> list = bookService.getAllBook();
        return new ModelAndView("bookList", "book", list);
    }

    @GetMapping("/manage_books")
    public ModelAndView getAllBooksAdmin() {
        // This method handles GET requests for the "/manage_books" URL.
        // It retrieves a list of all books using the BookService and renders the "adminBookList" view.
        List<Book> list = bookService.getAllBook();
        return new ModelAndView("adminBookList", "book", list);
    }

    @PostMapping("/save")
    public String addBook(@ModelAttribute Book b) {
        // This method handles POST requests for the "/save" URL.
        // It saves a book to the database using the BookService and then redirects to the book management page.
        bookService.save(b);
        return "redirect:/manage_books";
    }

    @RequestMapping("/adminEditBook/{id}")
    public String editBook(@PathVariable("id") int id, Model model) {
        // This method handles GET requests for the "/adminEditBook/{id}" URL, where {id} is a path variable.
        // It retrieves a book by ID and provides it to the "adminBookEdit" view for editing.
        Book book = bookService.getBookById(id);
        model.addAttribute("book", book);
        return "adminBookEdit";
    }

    @RequestMapping("/adminDeleteBook/{id}")
    public String deleteBook(@PathVariable("id") int id) {
        // This method handles GET requests for the "/adminDeleteBook/{id}" URL, where {id} is a path variable.
        // It deletes a book by ID using the BookService and then redirects to the book management page.
        bookService.deleteById(id);
        return "redirect:/manage_books";
    }
}
