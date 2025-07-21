package com.muhamadridwan.bookservice.controller;

import com.muhamadridwan.bookservice.dto.BookAddRequest;
import com.muhamadridwan.bookservice.dto.BookResponse;
import com.muhamadridwan.bookservice.dto.BookUpdateRequest;
import com.muhamadridwan.bookservice.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class BookController {
    private final BookService bookService;

    @GetMapping("/books")
    public ResponseEntity<Page<BookResponse>> getAllBuku(Pageable pageable) {
        return ResponseEntity.ok(bookService.getAllBook(pageable));
    }

    @PostMapping("/books")
    public ResponseEntity<Void> addBook(@Valid @RequestBody BookAddRequest request) {
        Long bookId = bookService.addBook(request);
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("bookId", bookId.toString());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<BookResponse> getBukuById(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.getBookById(id));
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/books")
    public ResponseEntity<BookResponse> updateBook(@Valid @RequestBody BookUpdateRequest request) {
        return ResponseEntity.ok(bookService.updateBook(request));
    }

}
