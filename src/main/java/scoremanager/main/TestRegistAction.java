package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import bean.Subject;
import bean.Teacher;
import bean.Test;
import dao.ClassNumDao;
import dao.SubjectDao;
import dao.TestDao;
import tool.Action;

public class TestRegistAction extends Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession session = request.getSession();
		Teacher teacher = (Teacher)session.getAttribute("teacher");
		if (teacher == null) {
			response.sendRedirect("Login.action");
			return;
		}
		String entYearStr = "";
		String classNum = "";
		String classSub = "";
		String classCon = "";
		int entYear = 0;
		LocalDate todaysDate = LocalDate.now();
		int year = todaysDate.getYear();
		ClassNumDao cNumDao = new ClassNumDao();
		SubjectDao subDao = new SubjectDao();
		TestDao tDao = new TestDao();
		Subject subject = new Subject();
		List<Test> tests = null;
		Map<String,String> errors = new HashMap<>();
		
		entYearStr = request.getParameter("f1");
		classNum = request.getParameter("f2");
		classSub = request.getParameter("f3");
		classCon = request.getParameter("f4");
		if (entYearStr != null) {
			entYear = Integer.parseInt(entYearStr);
		}
		List<Integer> entYearSet = new ArrayList<>();
		// 10年前から1年後まで
		for (int i = year-10; i < year+1; i++) {
			entYearSet.add(i);
		}
		
		List<String> list = cNumDao.filter(teacher.getSchool());
		List<Subject> subjects = subDao.filter(teacher.getSchool());
		List<String> counts = new ArrayList<>();
		counts.add("1");
		counts.add("2");
		String mode = request.getParameter("mode");
		if ("search".equals(mode)) {
			if (entYear != 0 && !classNum.equals("0") && !classSub.equals("0") && !classCon.equals("0")) {
				for (Subject sub : subjects) {
					if (sub.getName().equals(classSub)) {
						subject = sub;
						break;
					}
				}
				int classConInt = Integer.parseInt(classCon);
				tests = tDao.filter(entYear, classNum, subject, classConInt, teacher.getSchool());
				session.setAttribute("tests",tests);
				session.setAttribute("subject", subject);
				session.setAttribute("no", classConInt);
			} else {
				errors.put("f1","入学年度とクラスと科目と回数を選択してください");
				request.setAttribute("errors",errors);
			}
		}
		request.setAttribute("f1", entYear);
		request.setAttribute("f2", classNum);
		request.setAttribute("f3", classSub);
		request.setAttribute("f4", classCon);
		request.setAttribute("tests", tests);
		request.setAttribute("ent_year_set", entYearSet);
		request.setAttribute("class_num_set", list);
		request.setAttribute("class_sub_set", subjects);
		request.setAttribute("class_con_set", counts);
		
		request.getRequestDispatcher("scoremanager/main/test_regist.jsp").forward(request, response);
	}
}










//package scoremanager.main;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.HttpSession;
//
//import bean.Student;
//import bean.Subject;
//import bean.Teacher;
//import bean.Test;
//import dao.ClassNumDao;
//import dao.StudentDao;
//import dao.SubjectDao;
//import dao.TestDao;
//import tool.Action;
//
//public class TestRegistAction extends Action {
//
//    @Override
//    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
//
//        HttpSession session = request.getSession();
//        Teacher teacher = (Teacher) session.getAttribute("teacher");
//
//        // ✅ 未ログインチェック
//        if (teacher == null) {
//            response.sendRedirect("Login.action");
//            return;
//        }
//
//        // ============================
//        // 🔽 パラメータ取得
//        // ============================
//        String entYearStr = request.getParameter("f1");
//        String classNum   = request.getParameter("f2");
//        String classSub   = request.getParameter("f3");
//        String classCon   = request.getParameter("f4");
//
//        int entYear = 0;
//        if (entYearStr != null && !entYearStr.isEmpty()) {
//            try {
//                entYear = Integer.parseInt(entYearStr);
//            } catch (NumberFormatException e) {
//                entYear = 0;
//            }
//        }
//
//        // ============================
//        // 🔽 DAO
//        // ============================
//        ClassNumDao cNumDao = new ClassNumDao();
//        SubjectDao subDao   = new SubjectDao();
//        TestDao tDao        = new TestDao();
//
//        Subject subject = null;
//        List<Test> tests = null;
//
//        Map<String, String> errors = new HashMap<>();
//
//        // ============================
//        // 🔽 年リスト
//        // ============================
//        LocalDate today = LocalDate.now();
//        int year = today.getYear();
//
//        List<Integer> entYearSet = new ArrayList<>();
//        for (int i = year - 10; i <= year; i++) {
//            entYearSet.add(i);
//        }
//
//        List<String> classNumSet = cNumDao.filter(teacher.getSchool());
//        List<Subject> subjects   = subDao.filter(teacher.getSchool());
//        List<String> counts      = List.of("1", "2");
//
//        String mode = request.getParameter("mode");
//
//        // ============================
//        // ✅ 検索処理
//        // ============================
//        if ("search".equals(mode)) {
//
//            if (entYear != 0 &&
//                classNum != null && !classNum.equals("0") &&
//                classSub != null && !classSub.equals("0") &&
//                classCon != null && !classCon.equals("0")) {
//
//                subject = subDao.get(teacher.getSchool().getCd(), classSub);
//                int no = Integer.parseInt(classCon);
//
//                tests = tDao.filter(entYear, classNum, subject, no, teacher.getSchool());
//
//                // ✅ セッション保存
//                session.setAttribute("subject", subject);
//                session.setAttribute("no", no);
//                session.setAttribute("classNum", classNum);
//
//            } else {
//                errors.put("f1", "検索条件をすべて選択してください");
//            }
//        }
//
//        // ============================
//        // ✅ 登録処理
//        // ============================
//        else if ("save".equals(mode)) {
//
//            // 🔥 修正済み（最重要）
//            String[] studentNos = request.getParameterValues("studentNos");
//
//            if (studentNos == null) {
//                response.sendRedirect("TestRegist.action");
//                return;
//            }
//
//            Subject sub = (Subject) session.getAttribute("subject");
//
//            Integer noObj = (Integer) session.getAttribute("no");
//            int no = (noObj != null) ? noObj : 0;
//
//            String classNumSession = (String) session.getAttribute("classNum");
//
//            StudentDao stuDao = new StudentDao();
//            List<Test> list = new ArrayList<>();
//
//            for (String studentNo : studentNos) {
//
//                String pointStr = request.getParameter("point_" + studentNo);
//
//                int point = 0;
//                try {
//                    if (pointStr != null && !pointStr.isEmpty()) {
//                        point = Integer.parseInt(pointStr);
//                    }
//                } catch (NumberFormatException e) {
//                    point = 0;
//                }
//
//                Student student = stuDao.get(studentNo);
//
//                Test test = new Test();
//                test.setStudent(student);
//                test.setSubject(sub);
//                test.setSchool(teacher.getSchool());
//                test.setNo(no);
//                test.setPoint(point);
//                test.setClassNum(classNumSession);
//
//                list.add(test);
//            }
//
//            // ✅ 保存
//            tDao.save(list);
//
//            // ✅ リダイレクト
//            response.sendRedirect("TestRegist.action");
//            return;
//        }
//
//        // ============================
//        // ✅ JSPへ
//        // ============================
//        request.setAttribute("tests", tests);
//        request.setAttribute("errors", errors);
//
//        request.setAttribute("f1", entYear);
//        request.setAttribute("f2", classNum);
//        request.setAttribute("f3", classSub);
//        request.setAttribute("f4", classCon);
//
//        request.setAttribute("ent_year_set", entYearSet);
//        request.setAttribute("class_num_set", classNumSet);
//        request.setAttribute("class_sub_set", subjects);
//        request.setAttribute("class_con_set", counts);
//
//        request.getRequestDispatcher("scoremanager/main/test_regist.jsp")
//               .forward(request, response);
//    }
//}