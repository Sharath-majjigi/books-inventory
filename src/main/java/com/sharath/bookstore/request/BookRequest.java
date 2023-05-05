package com.sharath.bookstore.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookRequest {

    @NotEmpty(message = "Book name cannot be empty")
    public String bookName;

    @NotEmpty(message = "A proper description is required.")
    public String description;

    @NotNull(message = "Price required !")
    public Double price;

    public String author;

}
