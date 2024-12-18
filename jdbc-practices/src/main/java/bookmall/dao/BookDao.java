package bookmall.dao;

import bookmall.vo.BookVo;

import java.sql.*;

public class BookDao {
    public void deleteByNo(Long no) {
        try {
            Connection conn = null;
            PreparedStatement pstmt = null;

            try {
                conn = getConnection();

                String sql = "delete" +
                        " from book" +
                        " where id = ?";
                pstmt = conn.prepareStatement(sql);

                pstmt.setLong(1, no);

                pstmt.executeUpdate();

            } catch (SQLException e) {
                System.out.println("error:" + e);
            } finally {
                try {
                    if (conn != null) {
                        conn.close();
                    }
                    if (pstmt != null) {
                        pstmt.close();
                    }
                } catch (SQLException e) {
                    System.out.println("error:" + e);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void insert(BookVo vo) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = getConnection();

            String sql = "insert into book values(null, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pstmt.setLong(1, vo.getCategoryNo());
            pstmt.setString(2, vo.getTitle());
            pstmt.setLong(3, vo.getPrice());
            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                vo.setNo(rs.getLong(1));
            }

        } catch (SQLException e) {
            System.out.println("error:" + e);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException e) {
                System.out.println("error:" + e);
            }
        }
    }

    private Connection getConnection() throws SQLException {
        Connection conn = null;
        try {
            Class.forName("org.mariadb.jdbc.Driver");

            String url = "jdbc:mariadb://192.168.64.2/bookmall";
            conn = DriverManager.getConnection(url, "bookmall", "bookmall");
        } catch (ClassNotFoundException e) {
            System.out.println("드라이버 로딩 실패: " + e);
        }

        return conn;
    }
}
