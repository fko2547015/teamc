package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import bean.Student;
import bean.Teacher;
import dao.ClassNumDao;
import dao.StudentDao;
import tool.Action;

public class StudentCreateExecuteAction extends Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession session = request.getSession();
		Teacher teacher = null;
		if (session != null) {
			teacher = (Teacher)session.getAttribute("teacher");
		} else {
			response.sendRedirect("Login.action");
			return;
		}
		String entYearStr = "";
		String no = "";
		String name = "";
		String classNum = "";
		int entYear = 0;
		StudentDao sDao = new StudentDao();
		
		entYearStr = request.getParameter("ent_year");
		no = request.getParameter("no");
		name = request.getParameter("name");
		classNum = request.getParameter("class_num");
		
		if (entYearStr != null && !entYearStr.equals("")) {
			entYear = Integer.parseInt(entYearStr);
		}
		if (entYearStr == null || entYearStr.equals("")) {
			request.setAttribute("no", no);
		    request.setAttribute("name", name);
		    request.setAttribute("class_num", classNum);
		    request.setAttribute("ent_year", entYearStr);
		    
		    LocalDate todaysDate = LocalDate.now();
			int year = todaysDate.getYear();
			ClassNumDao cNumDao = new ClassNumDao();
			List<Integer> entYearSet = new ArrayList<>();
			for (int i = year-10; i < year+10; i++) {
				entYearSet.add(i);
			}
			List<String> list = cNumDao.filter(teacher.getSchool());
		
			request.setAttribute("class_num_set", list);
			request.setAttribute("ent_year_set", entYearSet);

			request.setAttribute("error", "入学年度を選択してください");
			request.getRequestDispatcher("/scoremanager/main/student_create.jsp").forward(request, response);
			return;
		}
		Student student = new Student();
		student.setNo(no);
		student.setName(name);
		student.setEntYear(entYear);
		student.setClassNum(classNum);
		student.setAttend(true);
		student.setSchool(teacher.getSchool());
		sDao.save(student);
		
		request.getRequestDispatcher("/scoremanager/main/student_create_done.jsp").forward(request, response);
	}
}
