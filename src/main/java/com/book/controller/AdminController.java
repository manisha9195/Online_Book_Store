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

import com.book.entity.Book;
import com.book.entity.User;
import com.book.service.BookService;
import com.book.service.UserService;
//the AdminController class handles requests related to the admin interface, including book and user management.
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    BookService bookService;
    
    @Autowired
    private UserService userService;

    @GetMapping("/adminHome")
    public String adminHome() {
        // This method handles GET requests for the "/admin/adminHome" URL and returns the "admin_page" view.
        return "admin_page";
    }

    @GetMapping("/admin_book_register")
    public String bookRegister() {
        // This method handles GET requests for the "/admin/admin_book_register" URL and returns the "adminBookRegister" view.
        return "adminBookRegister";
    }

    @GetMapping("/available_books")
    public ModelAndView getAllBook() {
        // This method handles GET requests for the "/admin/available_books" URL.
        // It retrieves a list of all books using the BookService and renders the "bookList" view.
        List<Book> list = bookService.getAllBook();
        return new ModelAndView("bookList", "book", list);
    }

    @GetMapping("/manage_books")
    public ModelAndView getAllBooksAdmin() {
        // This method handles GET requests for the "/admin/manage_books" URL.
        // It retrieves a list of all books using the BookService and renders the "adminBookList" view.
        List<Book> list = bookService.getAllBook();
        return new ModelAndView("adminBookList", "book", list);
    }

    @PostMapping("/save")
    public String addBook(@ModelAttribute Book b) {
        // This method handles POST requests for the "/admin/save" URL.
        // It saves a book to the database using the BookService and then redirects to the book management page.
        bookService.save(b);
        return "redirect:/manage_books";
    }

    @RequestMapping("/adminEditBook/{id}")
    public String editBook(@PathVariable("id") int id, Model model) {
        // This method handles GET requests for the "/admin/adminEditBook/{id}" URL, where {id} is a path variable.
        // It retrieves a book by ID and provides it to the "adminBookEdit" view for editing.
        Book book = bookService.getBookById(id);
        model.addAttribute("book", book);
        return "adminBookEdit";
    }

    @RequestMapping("/adminDeleteBook/{id}")
    public String deleteBook(@PathVariable("id") int id) {
        // This method handles GET requests for the "/admin/adminDeleteBook/{id}" URL, where {id} is a path variable.
        // It deletes a book by ID using the BookService and then redirects to the book management page.
        bookService.deleteById(id);
        return "redirect:/manage_books";
    }
    
    @GetMapping("/manage_users")
    public ModelAndView getAllUsers() {
        // This method handles GET requests for the "/admin/manage_users" URL.
        // It retrieves a list of all users using the UserService and renders the "adminUserList" view.
        List<User> list = userService.getAllUsers();
        return new ModelAndView("adminUserList", "user", list);
    }
    
    @RequestMapping("/manage_users/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        // This method handles GET requests for the "/admin/manage_users/{id}" URL, where {id} is a path variable.
        // It deletes a user by ID using the UserService and then redirects to the user management page.
        userService.deleteById(id);
        return "redirect:/manage_users";
    }
}
