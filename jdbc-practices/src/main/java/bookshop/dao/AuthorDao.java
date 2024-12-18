package bookshop.dao;

import bookshop.vo.AuthorVo;

import java.sql.*;

public class AuthorDao {
    public int insert(AuthorVo vo) {
        int count = 0;
        try (Connection conn = getConnection();
             PreparedStatement pstmt1 = conn.prepareStatement("insert into author values(null, ?)");
             PreparedStatement pstmt2 = conn.prepareStatement("select last_insert_id() from dual");
             ) { // () 사이에는 close가 있는 것들만 작성할 수 있음.
             pstmt1.setString(1, vo.getName());
             count = pstmt1.executeUpdate();

            ResultSet rs = pstmt2.executeQuery();
            vo.setId(rs.next() ? rs.getLong(1) : null);
            rs.close();
        } catch (SQLException e) {
            System.out.println("error:" + e);
        }

        return count;
    }

    public int deleteById(Long id) {
        int count = 0;
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement("delete from author where id = ?");
        ) {
            pstmt.setLong(1, id);
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

    public int deleteAll() {
        int count = 0;
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement("delete from author");
        ) {
            count = pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("error:" + e);
        }
        return count;
    }

}
