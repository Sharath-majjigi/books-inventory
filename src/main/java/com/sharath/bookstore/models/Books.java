package com.sharath.bookstore.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "inventory")
@Entity
public class Books {

    @Id
    private String id;

    @Column(name = "book_name")
    private String bookName;

    private String description;

    private Double price;

    private String author;

    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;

}
