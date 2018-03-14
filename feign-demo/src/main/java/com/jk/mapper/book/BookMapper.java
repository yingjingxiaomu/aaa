package com.jk.mapper.book;

import com.jk.model.book.BookBean;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface BookMapper {

    @Select("SELECT * FROM t_book")
    List<BookBean> getBookList();

    @Select("SELECT * FROM t_book LIMIT #{tPage},#{tNumber}")
    List<BookBean> getBookByPageList(@Param("tPage") Integer tPage, @Param("tNumber") Integer tNumber);

    @Delete("delete from t_book where id = #{id}")
    void deleteBook(@Param("id") Integer id);

    @Insert("insert into t_book(name,putaway,info,type,ctime,heading,people) values(#{name},#{putaway},#{info},#{type},#{ctime},#{heading},#{people})")
    void addBook(BookBean b);

    @Delete("DELETE from t_book WHERE  id in (${ids})")
    void deleteAllBook(@Param("ids") String ids);

    @Update("UPDATE t_book SET name = #{name},info = #{info},type = #{type},heading = #{heading},putaway = #{putaway},people = #{people},ctime = #{ctime} WHERE id = #{id}")
    void updateBook(BookBean b);

    //批量删除
    @Delete({
            "<script>"
                    + "DELETE FROM t_book WHERE id IN  "
                    + "<foreach item='ids' index='index' collection='idss' open='(' separator=',' close=')'>"
                    +       "#{ids}"
                    + "</foreach>"
                    +"</script>"
    })
    void deleteAllBookForeach(@Param("idss") String[] idss);
}
