package com.muhamadridwan.bookservice.service.impl;

import com.muhamadridwan.bookservice.dto.BookAddRequest;
import com.muhamadridwan.bookservice.dto.BookResponse;
import com.muhamadridwan.bookservice.dto.BookUpdateRequest;
import com.muhamadridwan.bookservice.entity.Book;
import com.muhamadridwan.bookservice.exception.ResourceNotFoundException;
import com.muhamadridwan.bookservice.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookServiceImplTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookServiceImpl bookService;

    @Test
    void getBookById_shouldReturnBookResponse_whenBookExists() {
        Book book = new Book();
        book.setId(1L);
        book.setTitle("Laskar Pelangi");
        book.setAuthor("Andrea Hirata");

        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

        BookResponse response = bookService.getBookById(1L);

        assertEquals("Laskar Pelangi", response.getTitle());
        assertEquals("Andrea Hirata", response.getAuthor());
    }

    @Test
    void getBookById_shouldThrowException_whenBookNotFound() {
        when(bookRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            bookService.getBookById(99L);
        });
    }

    @Test
    void getAllBook_shouldReturnPaginatedBookResponses() {
        Book book1 = new Book(1L, "Book One", "Author One");
        Book book2 = new Book(2L, "Book Two", "Author Two");

        List<Book> books = Arrays.asList(book1, book2);
        Page<Book> bookPage = new PageImpl<>(books);
        Pageable pageable = PageRequest.of(0, 10);

        when(bookRepository.findAll(pageable)).thenReturn(bookPage);

        Page<BookResponse> response = bookService.getAllBook(pageable);

        assertEquals(2, response.getTotalElements());
        assertEquals("Book One", response.getContent().get(0).getTitle());
        assertEquals("Author Two", response.getContent().get(1).getAuthor());
    }

    @Test
    void addBook_shouldSaveBookAndReturnId() {
        BookAddRequest request = new BookAddRequest();
        request.setAuthor("Ridwan");
        request.setTitle("Java Microservices");

        Book savedBook = new Book();
        savedBook.setId(10L);
        savedBook.setAuthor("Ridwan");
        savedBook.setTitle("Java Microservices");

        when(bookRepository.save(any(Book.class))).thenReturn(savedBook);

        Long resultId = bookService.addBook(request);

        assertEquals(10L, resultId);
        verify(bookRepository).save(any(Book.class));
    }

    @Test
    void updateBook_shouldUpdateFields_whenBookExists() {
        Book existingBook = new Book();
        existingBook.setId(2L);
        existingBook.setTitle("Old Title");
        existingBook.setAuthor("Old Author");

        BookUpdateRequest request = new BookUpdateRequest();
        request.setId(2L);
        request.setTitle("New Title");
        request.setAuthor("New Author");

        when(bookRepository.findById(2L)).thenReturn(Optional.of(existingBook));
        when(bookRepository.save(any(Book.class))).thenAnswer(inv -> inv.getArgument(0));

        BookResponse updated = bookService.updateBook(request);

        assertEquals("New Title", updated.getTitle());
        assertEquals("New Author", updated.getAuthor());
    }

    @Test
    void updateBook_shouldThrow_whenBookNotFound() {
        BookUpdateRequest request = new BookUpdateRequest();
        request.setId(999L);
        request.setTitle("X");
        request.setAuthor("Y");

        when(bookRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> bookService.updateBook(request));
    }

    @Test
    void deleteBook_shouldDelete_whenBookExists() {
        Book book = new Book();
        book.setId(5L);
        book.setTitle("Book 5");

        when(bookRepository.findById(5L)).thenReturn(Optional.of(book));

        bookService.deleteBook(5L);

        verify(bookRepository).delete(book);
    }

    @Test
    void deleteBook_shouldThrow_whenBookNotFound() {
        when(bookRepository.findById(404L)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> bookService.deleteBook(404L));
    }
}