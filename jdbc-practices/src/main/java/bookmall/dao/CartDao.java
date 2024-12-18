package bookmall.dao;

import bookmall.vo.CartVo;
import java.sql.*;
import java.util.ArrayList;
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
        ResultSet rs = null;
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
        List<CartVo> result = new ArrayList<>();

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();

            String sql = "select user_id, book_id, quantity, c.price, title" +
                    " from cart c join book b" +
                    " on c.book_id = b.id" +
                    " where user_id = ?";

            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, no);

            rs = pstmt.executeQuery();
            while (rs.next()) {
                Long usetId = rs.getLong(1);
                Long bookId = rs.getLong(2);
                int quantity = rs.getInt(3);
                int price = rs.getInt(4);
                String title = rs.getString(5);

                CartVo vo = new CartVo();
                vo.setUserNo(usetId);
                vo.setBookNo(bookId);
                vo.setQuantity(quantity);
                vo.setPrice(price);
                vo.setBookTitle(title);

                result.add(vo);
            }
        } catch (SQLException e) {
            System.out.println("error:" + e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
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
        return result;
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
