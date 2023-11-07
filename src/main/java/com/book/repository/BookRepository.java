

package com.book.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.book.entity.Book;

// The @Repository annotation is used to indicate that this interface is a Spring Data repository.
@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    
    // This repository interface extends JpaRepository, which provides CRUD (Create, Read, Update, Delete) operations
    // for the 'Book' entity. It also specifies the entity type (Book) and the type of the primary key (Integer).

    // The method signature 'Book findById(Long id)' is not required in this case because JpaRepository already
    // provides standard CRUD methods. You can use methods like 'findById' directly without defining them here.
}
