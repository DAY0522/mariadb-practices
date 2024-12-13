package bookmall.dao;

import bookmall.vo.CategoryVo;
import bookmall.vo.UserVo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDao {
    public void insert(CategoryVo vo) {

        Connection conn = null;
        PreparedStatement pstmt = null;
        Long autoId = null;
        try {
            conn = getConnection();

            String sql = "insert into category values(null, ?)";
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, vo.getName());
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
                        " from category" +
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

    public Long findNoByTitle(String title) {
        return null;
    }

    public List<CategoryVo> findAll() {
        List<CategoryVo> result = new ArrayList<>();

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();

            String sql = "select id, name" +
                    " from category" +
                    " order by id";
            pstmt = conn.prepareStatement(sql);

            rs = pstmt.executeQuery();
            while (rs.next()) {
                Long id = rs.getLong(1);
                String name = rs.getString(2);

                CategoryVo vo = new CategoryVo();
                vo.setNo(id);
                vo.setName(name);

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
