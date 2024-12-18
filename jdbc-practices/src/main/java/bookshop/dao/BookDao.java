package bookshop.dao;

import bookshop.vo.BookVo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDao {
    public int insert(BookVo vo) {
        int count = 0;
        try (
                Connection conn = getConnection();
                PreparedStatement pstmt1 = conn.prepareStatement("insert into book(title, author_id) values(?, ?)");
                PreparedStatement pstmt2 = conn.prepareStatement("select last_insert_id() from dual");
        ) { // () 사이에는 close가 있는 것들만 작성할 수 있음.
            pstmt1.setString(1, vo.getTitle());
            pstmt1.setLong(2, vo.getAuthorId());
            count = pstmt1.executeUpdate();

            ResultSet rs = pstmt2.executeQuery();
            vo.setId(rs.next() ? rs.getLong(1) : null);
            rs.close();
        } catch (SQLException e) {
            System.out.println("error:" + e);
        }

        return count;
    }

    public List<BookVo> findAll() {
        List<BookVo> result = new ArrayList<>();
        try (
                Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement("select a.id, a.title, b.name, a.status\n" +
                        "\tfrom book a join author b on a.author_id = b.id;");
        ) { // () 사이에는 close가 있는 것들만 작성할 수 있음.
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                Long id = rs.getLong(1);
                String title = rs.getString(2);
                String authorName = rs.getString(3);
                String status = rs.getString(4);

                BookVo vo = new BookVo();
                vo.setId(id);
                vo.setTitle(title);
                vo.setAuthorName(authorName);
                vo.setStatus(status);

                result.add(vo);
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("error:" + e);
        }
        return result;
    }

    public int update(Long id, String status) {
        int count = 0;
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement("update book set status = ? where id = ?");
        ) { // () 사이에는 close가 있는 것들만 작성할 수 있음.
            pstmt.setString(1, status);
            pstmt.setLong(2, id);
            count = pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("error:" + e);
        }

        return count;
    }

    public int deleteAll() {
        int count = 0;

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement("delete from book");
        ) {
            count = pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("error:" + e);
        }
        return count;
    }

    private Connection getConnection() throws SQLException {
        Connection conn = null;
        try {
            Class.forName("org.mariadb.jdbc.Driver");

            String url = "jdbc:mariadb://192.168.64.2/webdb";
            conn = DriverManager.getConnection(url, "webdb", "webdb");
        } catch (ClassNotFoundException e) {
            System.out.println("드라이버 로딩 실패: " + e);
        }

        return conn;
    }
}