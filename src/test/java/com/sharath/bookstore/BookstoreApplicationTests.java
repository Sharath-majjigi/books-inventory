package com.sharath.bookstore;

import com.sharath.bookstore.models.Books;
import com.sharath.bookstore.repository.BookDAO;
import com.sharath.bookstore.response.BookResponse;
import com.sharath.bookstore.service.BookService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class BookstoreApplicationTests {

    @Autowired
    private BookService bookService;

    @MockBean
    private BookDAO bookDAO;

    @Autowired
    private ModelMapper modelMapper;


    @BeforeEach
    public void setUp() {
        // Create mock data for testing
        List<Books> mockBooks = new ArrayList<>();
        mockBooks.add(new Books("1","The Great Gatsby","Desc1",120.0, "F. Scott Fitzgerald",new Timestamp(System.currentTimeMillis()),new Timestamp(System.currentTimeMillis())));
        mockBooks.add(new Books("2","To Kill a Mocking Bird","Desc2",120.5, "Harper lee",new Timestamp(System.currentTimeMillis()),new Timestamp(System.currentTimeMillis())));


        // Configure the mock repository to return the mock data
        Mockito.when(bookDAO.findAll()).thenReturn(mockBooks);
        Mockito.when(bookDAO.findById("1")).thenReturn(Optional.ofNullable(mockBooks.get(0)));
        Mockito.when(bookDAO.findByAuthorContainingOrBookNameContaining(null,null)).thenReturn(mockBooks);
        Mockito.when(bookDAO.findByAuthorContainingOrBookNameContaining("F. Scott Fitzgerald",null)).thenReturn(List.of(mockBooks.get(0)));
        Mockito.when(bookDAO.findByAuthorContainingOrBookNameContaining(null,"The Great Gatsby")).thenReturn(List.of(mockBooks.get(0)));
    }

    @Test
    public void getBooksByFilter_withNullFilter_shouldReturnAllBooks() {
        // Invoke the service method with null filters
        ResponseEntity<List<BookResponse>> books =  bookService.getBooksByFilter(null, null);

        // Verify that all books were returned
        assertThat(books.getBody()).hasSize(2);
    }

    @Test
    public void getBooksByFilter_withAuthorFilter_shouldReturnMatchingBooks() {
        // Invoke the service method with an author filter
        ResponseEntity<List<BookResponse>> books1 = bookService.getBooksByFilter("F. Scott Fitzgerald", null);
        ResponseEntity<List<BookResponse>> books2 = bookService.getBooksByFilter(null, "The Great Gatsby");
        // Verify that only the matching book was returned
        assertThat(books1.getBody()).hasSize(1);
        assertThat(books1.getBody().get(0).getBookName()).isEqualTo("The Great Gatsby");
        assertThat(books2.getBody()).hasSize(1);
        assertThat(books2.getBody().get(0).getAuthor()).isEqualTo("F. Scott Fitzgerald");

    }

    @Test
    public void getAllBooks(){
        ResponseEntity<List<BookResponse>> books=bookService.getAllBooks();

        //verify that it should return 2 books
        assertThat(books.getBody()).hasSize(2);
    }

    @Test
    public void getBookById_ShouldReturnBookWith_Id(){
        ResponseEntity<BookResponse> book=bookService.getBookById("1");

        //verify that it should return 1 book
        assertThat(book.getBody()).isNotNull();

        //verify the details of the book
        assertThat(book.getBody().getBookName()).isEqualTo("The Great Gatsby");
        assertThat(book.getBody().getDescription()).isEqualTo("Desc1");
        assertThat(book.getBody().getPrice()).isEqualTo(120.0);
        assertThat(book.getBody().getAuthor()).isEqualTo("F. Scott Fitzgerald");

    }



}
