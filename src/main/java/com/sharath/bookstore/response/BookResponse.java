package com.sharath.bookstore.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookResponse {

    public Long id;

    public String bookName;

    public String description;

    public Double price;

    public String author;

    public Timestamp createdAt;

    public Timestamp updatedAt;
}
