package com.kuang.controller;

import com.kuang.dao.BookMapper;
import com.kuang.pojo.Books;
import com.kuang.service.BookService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    /**
     * 查询返回所有的数据
     * @param model
     * @return
     */
    @RequestMapping("/allBook")
    public String list(Model model){
        List<Books> list = bookService.queryAllBook();
        model.addAttribute("list",list);

        return "allBook";
    };

    /**
     * 跳转到添加书籍页面
     * @return
     */
    @RequestMapping("/toAddBook")
    public String toAddPage(){
        return "addBook";
    }

    /**
     * 添加书籍
     * @param books
     * @return
     */
    @RequestMapping("/addPage")
    public String addPage(Books books){
        bookService.addBook(books);
        return "redirect:/book/allBook";
    }

    /**
     * 跳转到修改页面
     * @param model
     * @param id
     * @return
     */
    @RequestMapping("/toUpdateBooks")
    public String toUpdateBooks(Model model,int id){
        //根据id查询数据并返回
        Books books = bookService.queryBookById(id);
        System.out.println(books);
        //将数据回填
        model.addAttribute("books",books);
        return "updatePage";
    }

    /**
     * 修改数据
     * @param model
     * @param books
     * @return
     */
    @RequestMapping("/updateBooks")
    public String updateBooks(Model model,Books books){
        System.out.println(books);
        //修改数据
        bookService.updateBook(books);
        //并将修改的数据返回给model

        Books books1 = bookService.queryBookById(books.getBookID());
        model.addAttribute("books",books1);

        return "redirect:/book/allBook";
    }

    /**
     * 根据id删除书籍
     * @param id
     * @return
     */
    @RequestMapping("/deleteBooks/{bookID}")
    public String deleteBooks(@PathVariable("bookID") int id){
        bookService.deleteBookById(id);

        return "redirect:/book/allBook";
    }

    /**
     * 根据书名查询书籍
     * @param bookName
     * @param model
     * @return
     */
    @RequestMapping("/queryBookName")
    public String queryBookNmae(@RequestParam("bookName") String bookName, Model model){
        Books books1 = bookService.queryBookName(bookName);
        //将根据名字查询到的书籍放入页面中
        List<Books> list = new ArrayList<Books>();
        list.add(books1);
        model.addAttribute("list",list);

        //进行判断，如果查询的书籍为空，则返回全部书籍
        if (books1 == null){
            model.addAttribute("msg","查询书籍为空");
            List<Books> lists = bookService.queryAllBook();
            model.addAttribute("list",lists);
        }
        //查询之后返回书籍页面
        return "allBook";
    }

}
