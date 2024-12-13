package bookmall.dao;

import bookmall.vo.CartVo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class CartDao {

    public void deleteByUserNoAndBookNo(Long userNo, Long bookNO) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = getConnection();

            String sql = "delete" +
                    " from cart" +
                    " where user_id = ?" +
                    " and book_id = ?";
            pstmt = conn.prepareStatement(sql);

            pstmt.setLong(1, userNo);
            pstmt.setLong(2, bookNO);

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
    }

    public void insert(CartVo vo) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = getConnection();

            String sql = "insert into cart values(?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);

            pstmt.setLong(1, vo.getUserNo());
            pstmt.setLong(2, vo.getBookNo());
            pstmt.setInt(3, vo.getQuantity());
            pstmt.setInt(4, vo.getPrice());
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
    }

    public List<CartVo> findByUserNo(Long no) {
        return null;
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
