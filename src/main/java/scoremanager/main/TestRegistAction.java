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
		Test test = new Test();
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
				int classConInt = Integer.parseInt(classCon);
				tests = tDao.filter(entYear, classNum, test.getSubject(), classConInt, teacher.getSchool());
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
