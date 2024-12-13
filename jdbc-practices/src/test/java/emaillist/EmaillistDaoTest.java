package emaillist;

import emaillist.dao.EmaillistDao;
import emaillist.vo.EmaillistVo;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EmaillistDaoTest {
    private static Long count = 0L;

    @BeforeAll
    public static void setUp() {
        List<EmaillistVo> list = new EmaillistDao().findAll();
        count = (long) list.size();
    }

    @Test
    @Order(1)
    public void insertTest() {
        EmaillistVo vo = new EmaillistVo();
        vo.setFirstName("둘");
        vo.setLastName("리");
        vo.setEmail("dooly@gmail.com");

        Boolean result = new EmaillistDao().insert(vo);

        assertTrue(result);
    }

    @Test
    @Order(2)
    public void findAllTest() {
        List<EmaillistVo> list = new EmaillistDao().findAll();
        assertEquals(1, list.size());
    }

    @Test
    @Order(3)
    public void deleteByEmailTest() {
        Boolean result = new EmaillistDao().deleteByEmail("dooly@gmail.com");
        assertTrue(result);
    }

    @AfterAll
    public static void cleanup() {
    }
}
