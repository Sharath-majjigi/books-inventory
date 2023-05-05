package com.sharath.bookstore.repository;

import com.sharath.bookstore.models.Books;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookDAO  extends JpaRepository<Books,String> {

    @Query("SELECT b FROM Books b WHERE (:name is null or b.bookName = :name) and (:author is null or b.author = :author)")
    List<Books> findBooksByBookNameAndAuthor(@Param("name") String name, @Param("author") String author);

    List<Books> findByAuthorContainingOrBookNameContaining(String author, String bookName);

}
