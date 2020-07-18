import com.kuang.pojo.Books;
import com.kuang.service.BookService;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class MyTest {

    /*@Test
    public void test(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        BookService bookServiceImpl = (BookService) context.getBean("BookServiceImpl");
        List<Books> list = bookServiceImpl.queryAllBook();

        for (Books books : list) {
            System.out.println(books);
        }


    }*/

    @Test
    public void test(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        BookService bookService = (BookService) context.getBean("BookService");

        Books books = new Books();
        books.setBookName("大人物");
        bookService.updateBook(books);
        List<Books> list = bookService.queryAllBook();
        for (Books books1 : list) {
            System.out.println(books1);
        }

    }
}
