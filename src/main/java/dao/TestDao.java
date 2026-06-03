package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Test;

public class TestDao extends Dao {

    // ============================
    // 🔷 1件取得
    // ============================
    public Test get(Student student, Subject subject, School school, int no) throws Exception {

        Test test = null;

        Connection connection = getConnection();
        PreparedStatement statement = null;
        ResultSet rSet = null;

        try {
            statement = connection.prepareStatement(
                "select * from test where student_no=? and subject_cd=? and school_cd=? and no=?"
            );

            statement.setString(1, student.getNo());
            statement.setString(2, subject.getCd());
            statement.setString(3, school.getCd());
            statement.setInt(4, no);

            rSet = statement.executeQuery();

            if (rSet.next()) {
                StudentDao studentDao = new StudentDao();
                SubjectDao subjectDao = new SubjectDao();

                test = new Test();
                test.setStudent(studentDao.get(rSet.getString("student_no")));
                test.setSubject(subjectDao.get(school.getCd(), rSet.getString("subject_cd")));
                test.setSchool(school);
                test.setNo(rSet.getInt("no"));
                test.setPoint(rSet.getInt("point"));
                test.setClassNum(rSet.getString("class_num"));
            }

        } finally {
            if (rSet != null) rSet.close();
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }

        return test;
    }

    // ============================
    // 🔷 一覧変換
    // ============================
    private List<Test> postFilter(ResultSet rSet, School school) throws Exception {

        List<Test> list = new ArrayList<>();

        StudentDao studentDao = new StudentDao();
        SubjectDao subjectDao = new SubjectDao();

        while (rSet.next()) {
            Test test = new Test();

            test.setStudent(studentDao.get(rSet.getString("student_no")));
            test.setSubject(subjectDao.get(school.getCd(), rSet.getString("subject_cd")));
            test.setSchool(school);
            test.setNo(rSet.getInt("no"));
            test.setPoint(rSet.getInt("point"));
            test.setClassNum(rSet.getString("class_num"));

            list.add(test);
        }

        return list;
    }

    // ============================
    // 🔷 検索
    // ============================
    public List<Test> filter(int entYear, String classNum, Subject subject, int num, School school) throws Exception {

        List<Test> list = new ArrayList<>();

        Connection connection = getConnection();
        PreparedStatement statement = null;
        ResultSet rSet = null;

        try {
            statement = connection.prepareStatement(
                "select t.* from test t join student s on t.student_no = s.no " +
                "where s.ent_year=? and t.class_num=? and t.subject_cd=? and t.no=? and t.school_cd=?"
            );

            statement.setInt(1, entYear);
            statement.setString(2, classNum);
            statement.setString(3, subject.getCd());
            statement.setInt(4, num);
            statement.setString(5, school.getCd());

            rSet = statement.executeQuery();
            list = postFilter(rSet, school);

            // ✅ 未登録の学生も追加（点数0）
            StudentDao sDao = new StudentDao();
            List<Student> students = sDao.filter(school, entYear, classNum, true);

            for (Student stu : students) {
                boolean exists = false;

                for (Test t : list) {
                    if (t.getStudent().getNo().equals(stu.getNo())) {
                        exists = true;
                        break;
                    }
                }

                if (!exists) {
                    Test test = new Test();
                    test.setStudent(stu);
                    test.setSubject(subject);
                    test.setSchool(school);
                    test.setNo(num);
                    test.setPoint(0);
                    test.setClassNum(classNum);

                    list.add(test);
                }
            }

        } finally {
            if (rSet != null) rSet.close();
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }

        return list;
    }

    // ============================
    // 🔷 一括保存
    // ============================
    public boolean save(List<Test> list) throws Exception {

        Connection connection = getConnection();
        int count = 0;

        try {
            for (Test test : list) {
                if (save(test, connection)) {
                    count++;
                }
            }
        } finally {
            if (connection != null) connection.close();
        }

        return count > 0;
    }

    // ============================
    // 🔷 保存（INSERT or UPDATE）
    // ============================
    private boolean save(Test test, Connection connection) throws Exception {

        PreparedStatement statement = null;

        try {
            // ✅ 既存チェック
            Test current = get(
                test.getStudent(),
                test.getSubject(),
                test.getSchool(),
                test.getNo()
            );

            if (current == null) {
                // ✅ INSERT
                statement = connection.prepareStatement(
                    "insert into test (student_no, subject_cd, school_cd, no, point, class_num) " +
                    "values (?, ?, ?, ?, ?, ?)"
                );

                statement.setString(1, test.getStudent().getNo());
                statement.setString(2, test.getSubject().getCd());
                statement.setString(3, test.getSchool().getCd());
                statement.setInt(4, test.getNo());
                statement.setInt(5, test.getPoint());
                statement.setString(6, test.getClassNum());

            } else {
                // ✅ UPDATE（class_numは条件にしない）
                statement = connection.prepareStatement(
                    "update test set point=?, class_num=? " +
                    "where student_no=? and subject_cd=? and school_cd=? and no=?"
                );

                statement.setInt(1, test.getPoint());
                statement.setString(2, test.getClassNum());
                statement.setString(3, test.getStudent().getNo());
                statement.setString(4, test.getSubject().getCd());
                statement.setString(5, test.getSchool().getCd());
                statement.setInt(6, test.getNo());
            }

            return statement.executeUpdate() > 0;

        } finally {
            if (statement != null) statement.close();
        }
    }
}