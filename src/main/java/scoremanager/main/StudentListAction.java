package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import bean.Student;
import bean.Teacher;
import dao.ClassNumDao;
import dao.StudentDao;
import tool.Action;

public class StudentListAction extends Action {
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
		String isAttendStr = "";
		int entYear = 0;
		boolean isAttend = false;
		List<Student> students = null;
		LocalDate todaysDate = LocalDate.now();
		int year = todaysDate.getYear();
		StudentDao sDao = new StudentDao();
		ClassNumDao cNumDao = new ClassNumDao();
		Map<String,String> errors = new HashMap<>();
		
		entYearStr = request.getParameter("f1");
		classNum = request.getParameter("f2");
		isAttendStr = request.getParameter("f3");
		
		if (entYearStr != null) {
			entYear = Integer.parseInt(entYearStr);
		}
		// リストを初期化
		List<Integer> entYearSet = new ArrayList<>();
		// 10年前から1年後までリストに追加
		for (int i = year-10; i < year+1; i++) {
			entYearSet.add(i);
		}
		
		// DBからデータ取得
		List<String> list = cNumDao.filter(teacher.getSchool());
		if (entYear != 0 && !classNum.equals("0")) {
			students = sDao.filter(teacher.getSchool(), entYear, classNum, isAttend);
		} else if (entYear != 0 && classNum.equals("0")) {
			students = sDao.filter(teacher.getSchool(), entYear, classNum, isAttend);
		} else if (entYear == 0 && classNum == null || entYear == 0 && classNum.equals("0")) {
			students = sDao.filter(teacher.getSchool(), isAttend);
		} else {
			errors.put("f1", "クラスを指定する場合は入学年度も指定してください");
			request.setAttribute("errors",errors);
			students = sDao.filter(teacher.getSchool(), isAttend);
		}
		request.setAttribute("f1", entYear);
		request.setAttribute("f2", classNum);
		if (isAttendStr != null) {
			isAttend = true;
			request.setAttribute("f3", isAttendStr);
		}
		request.setAttribute("students", students);
		request.setAttribute("class_num_set", list);
		request.setAttribute("ent_year_set", entYearSet);
		
		request.getRequestDispatcher("/scoremanager/main/student_list.jsp").forward(request, response);
	}
}




// ログインしてなくても〇

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
//import bean.School;
//import bean.Student;
//import bean.Teacher;
//import dao.ClassNumDao;
//import dao.StudentDao;
//import tool.Action;
//
//public class StudentListAction extends Action {
//
//    @Override
//    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
//
//        HttpSession session = request.getSession();
//        Teacher teacher = (Teacher) session.getAttribute("user");
//        if (teacher == null) {
//            teacher = new Teacher();
//
//            School school = new School();
//            school.setCd("tes");   // ← DB にある SCHOOL_CD をセット
//
//            teacher.setSchool(school);
//        }
//
//        // パラメータ取得
//        String entYearStr = request.getParameter("f1");
//        String classNum = request.getParameter("f2");
//        String isAttendStr = request.getParameter("f3");
//
//        // null 対策
//        if (classNum == null) {
//            classNum = "0";
//        }
//
//        // 入学年度
//        int entYear = 0;
//        if (entYearStr != null && !entYearStr.isEmpty()) {
//            entYear = Integer.parseInt(entYearStr);
//        }
//
//        // 在籍フラグ
//        boolean isAttend = "1".equals(isAttendStr);
//
//        // 年度セット作成
//        LocalDate todaysDate = LocalDate.now();
//        int year = todaysDate.getYear();
//        List<Integer> entYearSet = new ArrayList<>();
//        for (int i = year - 10; i <= year; i++) {
//            entYearSet.add(i);
//        }
//
//        // DAO
//        StudentDao sDao = new StudentDao();
//        ClassNumDao cNumDao = new ClassNumDao();
//
//        // クラス番号一覧
//        List<String> classNumList = cNumDao.filter(teacher.getSchool());
//
//        // エラー用
//        Map<String, String> errors = new HashMap<>();
//
//        // 学生一覧
//        List<Student> students = null;
//
//        // 条件分岐（NullPointerException を防ぐ形に修正）
//        if (entYear != 0 && !classNum.equals("0")) {
//            students = sDao.filter(teacher.getSchool(), entYear, classNum, isAttend);
//
//        } else if (entYear != 0 && classNum.equals("0")) {
//            students = sDao.filter(teacher.getSchool(), entYear, classNum, isAttend);
//
//        } else if (entYear == 0 && classNum.equals("0")) {
//            students = sDao.filter(teacher.getSchool(), isAttend);
//
//        } else {
//            errors.put("f1", "クラスを指定する場合は入学年度も指定してください");
//            request.setAttribute("errors", errors);
//            students = sDao.filter(teacher.getSchool(), isAttend);
//        }
//
//        // JSP に渡す値
//        request.setAttribute("f1", entYear);
//        request.setAttribute("f2", classNum);
//        request.setAttribute("f3", isAttendStr);
//
//        request.setAttribute("students", students);
//        request.setAttribute("class_num_set", classNumList);
//        request.setAttribute("ent_year_set", entYearSet);
//
//        // ★絶対パス（あなたのフォルダ構造に合わせた）
//        request.getRequestDispatcher("/scoremanager/main/student_list.jsp")
//               .forward(request, response);
//    }
//}
