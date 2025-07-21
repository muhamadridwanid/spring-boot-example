package com.muhamadridwan.bookservice.repository;

import com.muhamadridwan.bookservice.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
