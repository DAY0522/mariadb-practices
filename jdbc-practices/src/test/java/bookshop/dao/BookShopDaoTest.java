package bookshop.dao;

import bookshop.vo.AuthorVo;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class BookShopDaoTest {
    private static AuthorVo vo = new AuthorVo();
    private static AuthorDao dao = new AuthorDao();

    @Test
    public void insertTest() {
        vo.setName("칼세이건");
        dao.insert(vo);

        assertNotNull(vo.getId());
    }

    @AfterAll
    public static void cleanUp() {
        dao.deleteById(vo.getId());
    }
}
