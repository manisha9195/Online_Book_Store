/*
	Manisha Macharla and kirtana Kuntla
*/

package com.book.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.book.entity.Book;
import com.book.entity.CartItem;
import com.book.entity.User;
import com.book.repository.CartItemRepository;
import com.book.repository.UserRepository;

@Service
public class ShoppingCartService {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private UserRepository userRepository;

    public List<CartItem> listCartItems(User user) {
        // Retrieve a list of cart items associated with the provided user using the findByUser() method
        // provided by the cartItemRepository.
        return cartItemRepository.findByUser(user);
    }

    public void addToCart(String username, Book selectedBook) {
        // Fetch the user by their email
        User user = userRepository.findByEmail(username);

        // Create a CartItem to represent the selected book in the shopping cart
        CartItem cartItem = new CartItem();
        
        // Set the associated book and user for the cart item
        cartItem.setBook(selectedBook);
        cartItem.setUser(user);
        
        // Set the initial quantity as needed (1 in this example)
        cartItem.setQuantity(1);

        // Save the cart item to the database
        cartItemRepository.save(cartItem);
    }

    public void deleteCartItemById(int cartItemId, String username) {
        // Fetch the user by their email
        User user = userRepository.findByEmail(username);

        // Retrieve the cart item by its ID
        Optional<CartItem> cartItem = cartItemRepository.findById(cartItemId);

        if (user != null && cartItem.isPresent()) {
            // Ensure that the cart item belongs to the user
            if (cartItem.get().getUser().getId() == user.getId()) {
                // Delete the cart item from the database
                cartItemRepository.delete(cartItem.get());
            }
        }
    }
}
