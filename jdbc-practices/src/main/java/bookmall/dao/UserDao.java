package bookmall.dao;

import bookmall.vo.UserVo;
import emaillist.vo.EmaillistVo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    public void insert(UserVo vo) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        Long autoId = null;
        try {
            conn = getConnection();

            String sql = "insert into user values(null, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pstmt.setString(1, vo.getName());
            pstmt.setString(2, vo.getMail());
            pstmt.setString(3, vo.getPassword());
            pstmt.setString(4, vo.getPhoneNumber());
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

    public void deleteByNo(Long no) {
        try {
            Connection conn = null;
            PreparedStatement pstmt = null;

            try {
                conn = getConnection();

                String sql = "delete" +
                        " from user" +
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

    public List<UserVo> findAll() {
        List<UserVo> result = new ArrayList<>();

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();

            String sql = "select id, name, mail, password, phonenumber" +
                    " from user" +
                    " order by id desc";
            pstmt = conn.prepareStatement(sql);

            rs = pstmt.executeQuery();
            while (rs.next()) {
                Long id = rs.getLong(1);
                String name = rs.getString(2);
                String mail = rs.getString(3);
                String password = rs.getString(4);
                String phonenumber = rs.getString(5);

                UserVo vo = new UserVo();
                vo.setNo(id);
                vo.setName(name);
                vo.setMail(mail);
                vo.setPassword(password);
                vo.setPhoneNumber(phonenumber);

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
