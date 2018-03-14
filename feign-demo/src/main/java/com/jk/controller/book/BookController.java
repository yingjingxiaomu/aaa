package com.jk.controller.book;

import com.jk.model.book.BookBean;
import com.jk.service.book.BookService;
import com.jk.utils.ConBean;
import com.jk.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "book")
public class BookController {

    @Autowired
    private BookService bookService;

    /**
     *
     * 方法: fileupload <br>
     * 描述: 图片上传 <br>
     * 作者: Teacher song<br>
     * 时间: 2018年01月19日 下午5:26:07
     * @param file
     * @return
     */
    @RequestMapping(value="fileupload")
    @ResponseBody
    public Map<String,Object> fileupload(@RequestParam MultipartFile file){
        Map<String,Object> map = new HashMap<String, Object>();
        try {
            String photoName = FileUtil.upFile(file, ConBean.IMG_PATH);
            String path = ConBean.IMG_SERVER_PATH+photoName;
            map.put("success", true);
            map.put("path", path);

        } catch (Exception e) {
            e.printStackTrace();
            map.put("success", false);
        }
        return map;
    }

    @RequestMapping(value = "toBookHtml")
    public String toBookHtml(){
        return "book/show_book";
    }


    /**
     * 分页查询书籍方法
     * @param
     * @param
     * @param
     * @return
     */
    @RequestMapping(value = "getBookList")
    @ResponseBody
    public String getBookList(Integer tPage, Integer tNumber){
        String booklist=bookService.getBookByPageList(tPage,tNumber);
        return  booklist;

    }

    /**
     * 删除书籍方法
     * @param id
     * @return
     */
    @RequestMapping(value = "deleteBook")
    @ResponseBody
    public void deleteBook(Integer id){
            bookService.deleteBook(id);
    }

    /**
     * 无效方法
     * @return
     */
    @RequestMapping(value = "toAddBookList")
    public String toAddBookList(){

        return "book/add_book";
    }

    /**
     * 添加书籍
     * @param b
     * @return
     */
    @RequestMapping(value = "addBook")
    @ResponseBody
    public Map<String,Object> addBook(BookBean b){
        Map<String,Object> map = new HashMap<String, Object>();
        try {
            if(b.getId()!=null){
                bookService.updateBook(b);
            }else {
                bookService.addBook(b);
            }
            map.put("success", true);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("success", false);
        }
        return map;

    }

    /**
     * 批量删除方法
     * @param ids
     * @return
     */
    @RequestMapping(value = "deleteAllBook")
    @ResponseBody
    public Map<String,Object> deleteAllBook(String ids) {
        Map<String,Object> map = new HashMap<String, Object>();
        try {
            bookService.deleteAllBook(ids);
            map.put("success", true);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("success", false);
        }
        return map;
    }



}
