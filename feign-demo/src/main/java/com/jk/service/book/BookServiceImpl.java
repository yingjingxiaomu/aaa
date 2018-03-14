package com.jk.service.book;


import com.alibaba.fastjson.JSONObject;
import com.jk.mapper.book.BookMapper;
import com.jk.model.book.BookBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookMapper bookMapper;

    public String getBookByPageList(Integer tPage, Integer tNumber) {
        tPage =(tPage-1)*tNumber;
        long total = bookMapper.getBookList().size();
        List<BookBean> list = bookMapper.getBookByPageList(tPage,tNumber);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 0);
        jsonObject.put("msg", "");
        jsonObject.put("count", total);
        jsonObject.put("data", list);

        return jsonObject.toString();
    }

    public void deleteBook(Integer id) {
        bookMapper.deleteBook(id);
    }

    public void deleteAllBook(String ids) {
        String[] idss = ids.split(",");
        //bookMapper.deleteAllBook(ids);
        bookMapper.deleteAllBookForeach(idss);
    }

    public void updateBook(BookBean b) {
        bookMapper.updateBook(b);
    }

    public void addBook(BookBean b) {
        bookMapper.addBook(b);
    }
}
