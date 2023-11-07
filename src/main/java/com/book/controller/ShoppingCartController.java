/*
	Manisha Macharla
*/
package com.book.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.book.entity.Book;
import com.book.entity.CartItem;
import com.book.entity.User;
import com.book.repository.UserRepository;
import com.book.service.BookService;
import com.book.service.ShoppingCartService;

@Controller
@RequestMapping("/user")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService cartService;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private BookService bookService;

    // Display the shopping cart for the user
    @GetMapping("/cart")
    public String showShoppingCart(Model model, Principal principal) {
        // This method handles GET requests for the "/user/cart" URL to display the user's shopping cart.
        // It takes the Model and Principal (for user information) as parameters.

        // Get the username of the currently logged-in user.
        String username = principal.getName();
        User currentUser = userRepository.findByEmail(username);

        // Retrieve the cart items for the current user.
        List<CartItem> cartItems = cartService.listCartItems(currentUser);

        // Calculate the total amount by summing the prices of items in the cart.
        double totalAmount = 0;
        for (CartItem cartItem : cartItems) {
            totalAmount += cartItem.getBook().getPrice() * cartItem.getQuantity();
        }

        // Add cart items, total amount, and other data to the model for rendering.
        model.addAttribute("cartItems", cartItems);
        model.addAttribute("totalAmount", totalAmount);

        return "shopping_cart"; // Render the "shopping_cart" view.
    }

    // Add a book to the user's cart
    @GetMapping("/myCart/{id}")
    public String addToCart(@PathVariable int id, Principal principal) {
        // This method handles GET requests for adding a book to the user's cart.
        // It takes the book ID and the Principal (for user information) as parameters.

        // Get the currently logged-in user.
        String username = principal.getName();

        // Retrieve the book with the given ID.
        Book selectedBook = bookService.getBookById(id);

        // Add the selected book to the user's cart.
        cartService.addToCart(username, selectedBook);

        // Redirect the user to the "Available Books" page or another appropriate page.
        return "redirect:/available_books";
    }

    // Delete a cart item from the user's cart
    @GetMapping("/deleteCartItem/{id}")
    public String deleteCartItem(@PathVariable int id, Principal principal) {
        // This method handles GET requests for deleting a cart item from the user's cart.
        // It takes the cart item ID and the Principal (for user information) as parameters.

        // Get the currently logged-in user.
        String username = principal.getName();

        // Delete the cart item by its ID.
        cartService.deleteCartItemById(id, username);

        // Redirect the user back to the shopping cart page.
        return "redirect:/user/cart";
    }
}
