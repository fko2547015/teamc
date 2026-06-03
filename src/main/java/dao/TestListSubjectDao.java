package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.School;
import bean.Subject;
import bean.TestListSubject;

public class TestListSubjectDao extends Dao {

    // ResultSet → Entity変換
    private List<TestListSubject> postFilter(ResultSet rSet) throws Exception {

        List<TestListSubject> list = new ArrayList<>();
        Map<String, TestListSubject> studentMap = new HashMap<>();

        while (rSet.next()) {

            String studentNo = rSet.getString("student_no");

            TestListSubject tls = studentMap.get(studentNo);

            if (tls == null) {
                tls = new TestListSubject();
                tls.setStudentNo(studentNo);
                tls.setStudentName(rSet.getString("student_name"));
                tls.setClassNum(rSet.getString("class_num"));
                tls.setEntYear(rSet.getInt("ent_year"));

                tls.setPoints(new HashMap<>());

                studentMap.put(studentNo, tls);
            }

            // ✅ 回数と点数
            int num = rSet.getInt("no");
            int point = rSet.getInt("point");

            tls.getPoints().put(num, point);
        }

        list.addAll(studentMap.values());

        return list;
    }

    // 科目検索
    public List<TestListSubject> filter(int entYear, String classNum, Subject subject, School school) throws Exception {

        List<TestListSubject> list = new ArrayList<>();

        Connection con = getConnection();
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            String sql = """
                SELECT
                    s.no AS student_no,
                    s.name AS student_name,
                    s.class_num,
                    s.ent_year,
                    t.no,
                    t.point
                FROM test t
                JOIN student s
                  ON t.student_no = s.no
                 AND t.school_cd = s.school_cd
                WHERE s.ent_year = ?
                  AND s.class_num = ?
                  AND t.subject_cd = ?
                  AND t.school_cd = ?
                ORDER BY s.no, t.no
            """;

            st = con.prepareStatement(sql);

            st.setInt(1, entYear);
            st.setString(2, classNum);
            st.setString(3, subject.getCd());
            st.setString(4, school.getCd());

            rs = st.executeQuery();

            list = postFilter(rs);

        } finally {
            if (rs != null) rs.close();
            if (st != null) st.close();
            if (con != null) con.close();
        }

        return list;
    }
}