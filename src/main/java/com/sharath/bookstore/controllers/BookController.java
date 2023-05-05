package com.sharath.bookstore.controllers;

import com.sharath.bookstore.request.BookRequest;
import com.sharath.bookstore.request.BookUpdateRequest;
import com.sharath.bookstore.response.BookResponse;
import com.sharath.bookstore.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping("/new-book")
    public ResponseEntity<?> createNewBook(@Valid @RequestBody BookRequest request){
        return bookService.createBook(request);
    }

    @GetMapping("/all-books")
    public ResponseEntity<List<BookResponse>> getAllBooks(){
        return bookService.getAllBooks();
    }


    @GetMapping("/{id}/book")
    public ResponseEntity<BookResponse> getBookById(@PathVariable String id){
        return bookService.getBookById(id);
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> updateBook(@PathVariable String id, @RequestBody BookUpdateRequest request){
        return bookService.updateBook(id,request);
    }


    @GetMapping
    public ResponseEntity<List<BookResponse>> getBooksByFilter(@RequestParam(required = false) String author, @RequestParam(required = false) String bookName) {
        return bookService.getBooksByFilter(author,bookName);
    }
}
