package com.yudiol.SpringDataJDBC.model;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class Book {

    private Long bookId;
    private String title;
    private String author;
    private int publicationYear;

}
