package com.macky.springbootshardingjdbc.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.macky.springbootshardingjdbc.entity.Book;
import com.macky.springbootshardingjdbc.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author ZhangPeng
 * @Title class BookController
 * @Description: TODO
 * @date 2019/7/12 20:53
 */
@RestController
public class BookController {

    @Autowired
    BookService bookService;

    @GetMapping(value = "/bookList")
    public Object index(@Valid Book query,
                        @RequestParam(value = "pageNum", defaultValue = "0") Integer pageNum,
                        @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {

        QueryWrapper<Book> wrapper = new QueryWrapper<>();
        wrapper.lambda().orderByDesc(Book::getId);

        // 查询第1页，每页返回10条
        Page<Book> page = new Page<>(pageNum, pageSize);

        return bookService.page(page, wrapper);
    }

    @RequestMapping(value = "/book", method = RequestMethod.GET)
    public List<Book> getItems(String name) {
        return bookService.getBookList(name);
    }

    @RequestMapping(value = "/book", method = RequestMethod.POST)
    public Boolean saveItem(@RequestBody Book book) {
        return bookService.save(book);
    }
}
