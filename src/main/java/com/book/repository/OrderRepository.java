package com.book.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.book.entity.Order;
import com.book.entity.User;

// The @Repository annotation is used to indicate that this interface is a Spring Data repository.
@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    // This repository interface extends JpaRepository, which provides standard CRUD (Create, Read, Update, Delete) operations
    // for the 'Order' entity. It also specifies the entity type (Order) and the type of the primary key (Integer).

    // The method signature 'List<Order> findByUser(User user)' is a custom method defined to retrieve a list of orders
    // associated with a specific user. This method is not part of the standard CRUD operations provided by JpaRepository
    // and is created to meet the specific requirements of fetching orders by user.
    List<Order> findByUser(User user);
}
