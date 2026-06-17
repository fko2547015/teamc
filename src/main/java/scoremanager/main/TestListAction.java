package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import bean.Student;
import bean.Subject;
import bean.Teacher;
import bean.TestListStudent;
import bean.TestListSubject;
import dao.ClassNumDao;
import dao.StudentDao;
import dao.SubjectDao;
import dao.TestListStudentDao;
import dao.TestListSubjectDao;
import tool.Action;

public class TestListAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // セッションチェック
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("teacher");

        if (teacher == null) {
            response.sendRedirect("Login.action");
            return;
        }

        // パラメータ取得
        String entYearStr = request.getParameter("f1");
        String classNum   = request.getParameter("f2");
        String classSub   = request.getParameter("f3");
        String studentNo  = request.getParameter("f4");

        // DAO
        ClassNumDao cNumDao = new ClassNumDao();
        SubjectDao subDao   = new SubjectDao();
        StudentDao stuDao   = new StudentDao();
        TestListStudentDao tlsDao = new TestListStudentDao();
        TestListSubjectDao tSubDao = new TestListSubjectDao();

        // 入学年度
        int entYear = 0;
        if (entYearStr != null && !entYearStr.isEmpty()) {
            entYear = Integer.parseInt(entYearStr);
        }

        // 科目
        Subject subject = null;
        if (classSub != null && !classSub.isEmpty()) {
            subject = subDao.get(teacher.getSchool().getCd(), classSub);
        }

        // 入学年度リスト（プルダウン用）
        LocalDate today = LocalDate.now();
        int year = today.getYear();

        List<Integer> entYearSet = new ArrayList<>();
        for (int i = year - 10; i <= year; i++) {
            entYearSet.add(i);
        }

        // 結果
        List<TestListStudent> studentTests = null;
        List<TestListSubject> subjectTests = null;

        // 分岐

        if (studentNo != null && !studentNo.isEmpty()) {
            // 学生検索
            Student student = stuDao.get(studentNo);

            if (student != null) {
                studentTests = tlsDao.filter(student);
            }

        } else if (subject != null && entYear > 0 && classNum != null && !classNum.isEmpty()) {
            // 科目検索（新DAO）
            subjectTests = tSubDao.filter(entYear, classNum, subject, teacher.getSchool());
        }

        // 選択肢
        List<String> classNumSet = cNumDao.filter(teacher.getSchool());
        List<Subject> subjects   = subDao.filter(teacher.getSchool());

        // 回数リスト（固定じゃないがとりあえず5回まで表示）
        List<Integer> numList = List.of(1, 2, 3, 4, 5);

        // JSPに渡す
        request.setAttribute("studentTests", studentTests);
        request.setAttribute("subjectTests", subjectTests);

        request.setAttribute("f1", entYear);
        request.setAttribute("f2", classNum);
        request.setAttribute("f3", classSub);
        request.setAttribute("f4", studentNo);

        request.setAttribute("ent_year_set", entYearSet);
        request.setAttribute("class_num_set", classNumSet);
        request.setAttribute("class_sub_set", subjects);
        request.setAttribute("numList", numList); // ★これ重要

        // ✅ 画面へ
        request.getRequestDispatcher("scoremanager/main/test_list.jsp")
               .forward(request, response);
    }
}