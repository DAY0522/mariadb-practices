package bookshop.dao;

import bookshop.vo.AuthorVo;
import bookshop.vo.BookVo;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class BookDaoTest {
    private static AuthorDao authorDao = new AuthorDao();
    private static BookDao bookDao = new BookDao();
    private static AuthorVo mockAuthorVo = new AuthorVo();

    @BeforeAll
    public static void setUp() {
        mockAuthorVo.setName("칼세이건");
        authorDao.insert(mockAuthorVo);
    }

    @Test
    public void insertTest() {
        BookVo bookVo = new BookVo();
        bookVo.setTitle("코스모스");
        bookVo.setAuthorId(mockAuthorVo.getId());

        bookDao.insert(bookVo);
    }

    @AfterAll
    public static void cleanUp() {
        bookDao.deleteAll();
        authorDao.deleteById(mockAuthorVo.getId());
    }
}
