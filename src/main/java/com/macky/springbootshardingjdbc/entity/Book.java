package com.macky.springbootshardingjdbc.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonView;
import groovy.transform.EqualsAndHashCode;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.Pattern;

/**
 * @author ZhangPeng
 * @Title class Book
 * @Description: 书籍是实体类
 * @date 2019/7/13 15:23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("book")
public class Book extends Model<Book> {

    public interface BookViewForm {
    }

    public interface BookViewDetail {
    }


    @JsonView(BookViewForm.class)
    private int id;

    @JsonView(BookViewDetail.class)
    private String name;

    @JsonView(BookViewDetail.class)
    @Pattern(regexp = "^[1-9]\\d*$", message = "件数不合法")
    private String count;
}
