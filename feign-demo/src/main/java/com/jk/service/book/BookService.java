package com.jk.service.book;

import com.jk.model.book.BookBean;

import java.util.List;

public interface BookService {


    void addBook(BookBean b);

    String getBookByPageList(Integer tPage, Integer tNumber);

    void deleteBook(Integer id);

    void deleteAllBook(String ids);

    void updateBook(BookBean b);
}
