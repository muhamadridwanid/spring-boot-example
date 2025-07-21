package com.muhamadridwan.bookservice.service;

import com.muhamadridwan.bookservice.dto.BookAddRequest;
import com.muhamadridwan.bookservice.dto.BookResponse;
import com.muhamadridwan.bookservice.dto.BookUpdateRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookService {
    BookResponse getBookById(Long id);

    Page<BookResponse> getAllBook(Pageable pageable);

    Long addBook(BookAddRequest request);

    BookResponse updateBook(BookUpdateRequest request);

    void deleteBook(Long id);
}
