package com.muhamadridwan.bookservice.service.impl;

import com.muhamadridwan.bookservice.dto.BookAddRequest;
import com.muhamadridwan.bookservice.dto.BookResponse;
import com.muhamadridwan.bookservice.dto.BookUpdateRequest;
import com.muhamadridwan.bookservice.entity.Book;
import com.muhamadridwan.bookservice.exception.ResourceNotFoundException;
import com.muhamadridwan.bookservice.repository.BookRepository;
import com.muhamadridwan.bookservice.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    public BookResponse getBookById(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Book is not found!")
        );
        return mapResponse(book);
    }

    @Override
    public Page<BookResponse> getAllBook(Pageable pageable) {
        return bookRepository.findAll(pageable)
                .map(this::mapResponse);
    }

    @Override
    public Long addBook(BookAddRequest request) {
        Book book = new Book();
        book.setAuthor(request.getAuthor());
        book.setTitle(request.getTitle());
        return bookRepository.save(book).getId();
    }

    @Override
    public BookResponse updateBook(BookUpdateRequest request) {
        Book book = bookRepository.findById(request.getId()).orElseThrow(
                () -> new IllegalArgumentException("Invalid book id")
        );

        if (!request.getAuthor().isEmpty()) {
            book.setAuthor(request.getAuthor());
        }

        if (!request.getTitle().isEmpty()) {
            book.setTitle(request.getTitle());
        }

        return mapResponse(bookRepository.save(book));
    }

    @Override
    public void deleteBook(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Invalid book id")
        );

        bookRepository.delete(book);
    }

    private BookResponse mapResponse(Book book) {
        return new BookResponse(
                book.getId(),
                book.getTitle(),
                book.getAuthor()
        );
    }
}
