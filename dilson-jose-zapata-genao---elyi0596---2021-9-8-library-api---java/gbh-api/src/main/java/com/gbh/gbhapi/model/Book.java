package com.gbh.gbhapi.model;


import lombok.*;

import java.util.List;


@Getter
@Setter
public class Book {

    private String title;
    private int idBook;
    private List<Page> pages;
    private String author;

}
