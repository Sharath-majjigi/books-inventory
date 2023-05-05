package com.sharath.bookstore.service;

import com.sharath.bookstore.exception.ResourceNotFoundException;
import com.sharath.bookstore.models.Books;
import com.sharath.bookstore.repository.BookDAO;
import com.sharath.bookstore.request.BookRequest;
import com.sharath.bookstore.request.BookUpdateRequest;
import com.sharath.bookstore.response.BookResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.beans.FeatureDescriptor;
import java.math.BigInteger;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookDAO bookDAO;

    private final ModelMapper modelMapper;

    public ResponseEntity<?> createBook(BookRequest bookRequest){
        Books newBook=modelMapper.map(bookRequest,Books.class);
        String random_sequence= String.format("%040d",new BigInteger(UUID.randomUUID().toString().replace("-",""),16));
        newBook.setId(random_sequence.substring(1,7));
        Books saved=bookDAO.save(newBook);
        BookResponse response=modelMapper.map(saved,BookResponse.class);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    public ResponseEntity<?> updateBook(String id, BookUpdateRequest request){
        Books existingBook=bookDAO.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book","Id",id));
        BeanUtils.copyProperties(request, existingBook, getNullPropertyNames(request));
        Books updatedBook=bookDAO.save(existingBook);
        BookResponse response=modelMapper.map(updatedBook,BookResponse.class);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    //Function to get which fields are null
    private static String[] getNullPropertyNames(Object source) {
        BeanWrapperImpl src = new BeanWrapperImpl(source);
        return Stream.of(src.getPropertyDescriptors())
                .map(FeatureDescriptor::getName)
                .filter(propertyName -> src.getPropertyValue(propertyName) == null)
                .toArray(String[]::new);
    }

    public ResponseEntity<BookResponse> getBookById(String id){
        Books existingBook=bookDAO.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book","Id",id));
        BookResponse foundBook=modelMapper.map(existingBook,BookResponse.class);
        return new ResponseEntity<>(foundBook,HttpStatus.OK);
    }

    public ResponseEntity<List<BookResponse>> getAllBooks(){
        List<Books> allBooks=bookDAO.findAll();
        List<BookResponse> responses=allBooks.stream().map(books -> modelMapper.map(books,BookResponse.class)).collect(Collectors.toList());
        return new ResponseEntity<>(responses,HttpStatus.OK);
    }

    public ResponseEntity<List<BookResponse>> getBooksByFilter(String author,String bookName) {
        List<Books> allBooksByFilter=bookDAO.findByAuthorContainingOrBookNameContaining(author,bookName);
        List<BookResponse> responses= allBooksByFilter.stream().map(books -> modelMapper.map(books, BookResponse.class)).toList();

        return new ResponseEntity<>(responses,HttpStatus.OK);
    }


}
