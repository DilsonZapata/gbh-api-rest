package com.gbh.gbhapi.model;

import com.gbh.gbhapi.model.Book;
import java.util.List;

public interface IBookResource {

    List<Book> listAll();
    Book findById(Integer id);
    Page findpagebyidbook(Integer id, Integer idPage);

}
