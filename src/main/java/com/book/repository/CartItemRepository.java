package com.book.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.book.entity.CartItem;
import com.book.entity.User;

public interface CartItemRepository extends JpaRepository<CartItem, Integer> {

    // This repository interface extends JpaRepository, which provides standard CRUD (Create, Read, Update, Delete) operations
    // for the 'CartItem' entity. It also specifies the entity type (CartItem) and the type of the primary key (Integer).

    // The method signature 'public List<CartItem> findByUser(User user)' is a custom method defined to retrieve a list
    // of cart items associated with a specific user. This method is not part of the standard CRUD operations provided
    // by JpaRepository and is created to meet the specific requirements of fetching cart items by user.
    public List<CartItem> findByUser(User user);
}
