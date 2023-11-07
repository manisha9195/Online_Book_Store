/*
	Manisha Macharla and kirtana Kuntla
*/

package com.book.service;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.book.entity.Order;
import com.book.entity.User;
import com.book.repository.OrderRepository;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    // Method to create an order
    public Order createOrder(User user, double totalAmount) {
        // Create a new Order
        Order order = new Order();
        
        // Set the user associated with the order
        order.setUser(user);
        
        // Set the order date to the current date
        order.setOrderDate(new Date());
        
        // Set the total amount for the order
        order.setTotalAmount(totalAmount);
        
        // Set the initial order status as "not delivered"
        order.setStatus("not delivered");

        // Save the order to the database using the orderRepository
        return orderRepository.save(order);
    }

    // Method to retrieve orders by user
    public List<Order> getOrdersByUser(User user) {
        // Retrieve a list of orders associated with the provided user using the findByUser() method
        // provided by the orderRepository.
        return orderRepository.findByUser(user);
    }
}
