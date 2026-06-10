package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Student;
import bean.TestListStudent;

public class TestListStudentDao extends Dao {

    // ResultSet → List<TestListStudent> に変換
    private List<TestListStudent> postFilter(ResultSet rSet) throws Exception {
        List<TestListStudent> list = new ArrayList<>();

        while (rSet.next()) {
            TestListStudent tls = new TestListStudent();
            tls.setSubjectName(rSet.getString("subject_name"));
            tls.setSubjectCd(rSet.getString("subject_cd"));
            tls.setNum(rSet.getInt("no"));
            tls.setPoint(rSet.getInt("point"));
            list.add(tls);
        }

        return list;
    }

    // 学生1人分の成績一覧を取得
    public List<TestListStudent> filter(Student student) throws Exception {
        List<TestListStudent> list = new ArrayList<>();

        Connection connection = getConnection();
        PreparedStatement statement = null;
        ResultSet rSet = null;

        try {
            String sql = """
                SELECT 
                    sub.name AS subject_name,
                    sub.cd AS subject_cd,
                    t.no,
                    t.point
                FROM test t
                JOIN subject sub
                  ON t.subject_cd = sub.cd
                 AND t.school_cd = sub.school_cd
                WHERE t.student_no = ?
                  AND t.school_cd = ?
                ORDER BY sub.cd, t.no
            """;

            statement = connection.prepareStatement(sql);
            statement.setString(1, student.getNo());
            statement.setString(2, student.getSchool().getCd());

            rSet = statement.executeQuery();
            list = postFilter(rSet);

        } finally {
            if (rSet != null) rSet.close();
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }

        return list;
    }
}
