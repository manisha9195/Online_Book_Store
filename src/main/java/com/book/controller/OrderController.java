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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.book.entity.Order;
import com.book.entity.User;
import com.book.repository.UserRepository;
import com.book.service.OrderService;

@Controller
@RequestMapping("/user")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserRepository userRepository;

    // Create an order
    @GetMapping("/placeOrder")
    public String placeOrder(Principal principal, Model model, @RequestParam("totalAmount") double totalAmount) {
        // This method handles GET requests for the "/user/placeOrder" URL to create a new order.
        // It takes the currently logged-in user's principal, the model, and the totalAmount as parameters.

        // Get the username of the currently authenticated user.
        String username = principal.getName();
        User user = userRepository.findByEmail(username);

        // Create a new order using the OrderService with the user and totalAmount.
        Order order = orderService.createOrder(user, totalAmount);

        // You can add additional logic here if needed, such as adding items to the order.

        // Redirect to the user's orders page.
        return "redirect:/user/orders";
    }

    // Retrieve orders for a user
    @GetMapping("/orders")
    public String viewOrders(Principal principal, Model model) {
        // This method handles GET requests for the "/user/orders" URL to retrieve and display orders for the user.
        // It takes the currently logged-in user's principal and the model as parameters.

        // Get the username of the currently authenticated user.
        String username = principal.getName();
        User user = userRepository.findByEmail(username);

        // Get a list of orders associated with the user using the OrderService.
        List<Order> orders = orderService.getOrdersByUser(user);

        // Add the list of orders to the model for rendering in the "orderList" view.
        model.addAttribute("orders", orders);

        return "orderList";
    }
}
