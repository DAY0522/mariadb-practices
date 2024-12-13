package bookmall.dao;

import bookmall.vo.OrderBookVo;
import bookmall.vo.OrderVo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDao {

    public void deleteBooksByNo(Long no) {

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = getConnection();

            String sql = "delete" +
                    " from orderbook" +
                    " where order_id = ?";
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
    }

    public void deleteByNo(Long no) {
        try {
            Connection conn = null;
            PreparedStatement pstmt = null;

            try {
                conn = getConnection();

                String sql = "delete" +
                        " from orders" +
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

    public List<OrderBookVo> findBooksByNoAndUserNo(Long orderNo, Long userNo) {
        List<OrderBookVo> result = new ArrayList<>();

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();

            String sql = "select book_id, order_id, quantity, o.price, title" +
                    " from orderbook o join book b" +
                    " on o.book_id = b.id" +
                    " where order_id = ?";

            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, orderNo);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                Long bookId = rs.getLong(1);
                Long orderId = rs.getLong(2);
                int quantity = rs.getInt(3);
                int price = rs.getInt(4);
                String title = rs.getString(5);

                OrderBookVo vo = new OrderBookVo();
                vo.setBookNo(bookId);
                vo.setOrderNo(orderNo);
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

    public void insert(OrderVo vo) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        Long autoId = null;
        try {
            conn = getConnection();

            String sql = "insert into orders values(null, ?, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pstmt.setLong(1, vo.getUserNo());
            pstmt.setString(2, vo.getNumber());
            pstmt.setInt(3, vo.getPayment());
            pstmt.setString(4, vo.getShipping());
            pstmt.setString(5, vo.getStatus());
            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                autoId = rs.getLong(1);
                vo.setNo(autoId);
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

    public void insertBook(OrderBookVo vo) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = getConnection();

            String sql = "insert into orderbook values(?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);

            pstmt.setLong(1, vo.getBookNo());
            pstmt.setLong(2, vo.getOrderNo());
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

    public OrderVo findByNoAndUserNo(Long no, Long userNo) {
        OrderVo vo = null;

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();

            String sql = "select id, user_id, number, payment, shipping, status" +
                    " from orders" +
                    " where id = ? and user_id = ?";

            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, no);
            pstmt.setLong(2, userNo);

            rs = pstmt.executeQuery();
            if (rs.next()) {
                vo = new OrderVo();
                Long id = rs.getLong(1);
                Long userIo = rs.getLong(2);
                String number = rs.getString(3);
                int payment = rs.getInt(4);
                String shipping = rs.getString(5);
                String status = rs.getString(6);

                vo.setNo(id);
                vo.setUserNo(userIo);
                vo.setNumber(number);
                vo.setPayment(payment);
                vo.setShipping(shipping);
                vo.setStatus(status);
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

        return vo;
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
