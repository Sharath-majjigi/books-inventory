package com.sharath.bookstore.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookUpdateRequest {

    public String bookName;

    public Double price;

    public String description;

    public String author;
}
