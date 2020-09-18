package cn.jelly.lightspace.service;

import cn.jelly.lightspace.entity.Book;
import cn.jelly.lightspace.mapper.BookMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BookService {
    @Resource
    BookMapper bookMapper;

    public PageInfo<Book> findAll(int  currentPage,int pageSize) {
        PageHelper.startPage(currentPage,pageSize, true);
        List<Book> bookList = bookMapper.findAll();
        PageInfo<Book> pageInfo = new PageInfo<>(bookList);
        return pageInfo;
    }

}
