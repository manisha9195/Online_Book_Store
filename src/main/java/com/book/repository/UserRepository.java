package com.book.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.book.entity.User;

// The @Repository annotation is used to indicate that this interface is a Spring Data repository.
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    // This repository interface extends JpaRepository, which provides standard CRUD (Create, Read, Update, Delete) operations
    // for the 'User' entity. It also specifies the entity type (User) and the type of the primary key (Integer).

    // The method signature 'public User findByEmail(String email)' is a custom method defined to retrieve a user by their email
    // address. This method is not part of the standard CRUD operations provided by JpaRepository and is created to meet the
    // specific requirements of finding a user by email.
    public User findByEmail(String email);
}
