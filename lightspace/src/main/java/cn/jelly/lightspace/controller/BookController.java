package cn.jelly.lightspace.controller;

import cn.jelly.lightspace.entity.Book;
import cn.jelly.lightspace.service.BookService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class BookController {
    @Autowired
    BookService bookService;

    @PostMapping(value = "api/bookDisplay")
    public PageInfo<Book> bookDisplay(@RequestParam int  currentPage,@RequestParam int pageSize) {
        return bookService.findAll(currentPage,pageSize);
    }
}
