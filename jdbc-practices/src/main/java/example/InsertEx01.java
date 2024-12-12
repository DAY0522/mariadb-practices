package example;

import java.sql.*;

public class InsertEx01 {
    public static void main(String[] args) {
        insert("개발1팀");
        insert("개발2팀");
    }

    public static boolean insert(String departmentName) {
        boolean result = false;
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            // 1. JDBC Driver 로딩
            Class.forName("org.mariadb.jdbc.Driver");

            // 2. 연결하기
            String url = "jdbc:mariadb://192.168.64.2/webdb";
            conn = DriverManager.getConnection(url, "webdb", "webdb");

            // 3. Statement 생성하기
            String sql = "insert into department values(null, ?)";
            pstmt = conn.prepareStatement(sql);

            // 4. SQL 실행
            int count = pstmt.executeUpdate(sql);

            result  = count == 1;
        } catch (ClassNotFoundException e) {
            System.out.println("드라이버 로딩 실패: " + e);
        } catch (SQLException e) {
            System.out.println("error: " + e);
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return result;
    }
}