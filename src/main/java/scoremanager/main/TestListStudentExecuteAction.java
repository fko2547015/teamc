//package scoremanager.main;
//
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//import bean.Student;
//import bean.Subject;
//import bean.Teacher;
//import bean.Test;
//
//import dao.StudentDao;
//import dao.SubjectDao;
//import dao.TestDao;
//
//import tool.Action;
//
//public class TestListStudentExecuteAction extends Action {
//
//    @Override
//    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
//
//        // ✅ パラメータ取得
//        String studentNo = request.getParameter("studentNo");
//        String subjectCd = request.getParameter("subjectCd");
//        String numStr    = request.getParameter("num");
//        String pointStr  = request.getParameter("point");
//
//        // ✅ 型変換
//        int num = Integer.parseInt(numStr);
//        int point = Integer.parseInt(pointStr);
//
//        // ✅ DAO準備
//        StudentDao stuDao = new StudentDao();
//        SubjectDao subDao = new SubjectDao();
//        TestDao testDao   = new TestDao();
//
//        // ✅ データ取得
//        Student student = stuDao.get(studentNo);
//        Subject subject = subDao.get(student.getSchool().getCd(), subjectCd);
//
//        // ✅ Testオブジェクト作成
//        Test test = new Test();
//        test.setStudent(student);
//        test.setSubject(subject);
//        test.setSchool(student.getSchool());
//        test.setNo(num);
//        test.setPoint(point);
//
//        // ✅ DB登録（ここ重要）
//        testDao.save(test);
//
//        // ✅ 一覧画面へ戻る
//        response.sendRedirect("TestList.action");
//    }
//}