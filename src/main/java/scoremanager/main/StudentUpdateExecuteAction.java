package scoremanager.main;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import bean.Student;
import bean.Teacher;
import dao.StudentDao;
import tool.Action;

public class StudentUpdateExecuteAction extends Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession session = request.getSession();
		Teacher teacher = (Teacher)session.getAttribute("teacher");
		if (teacher == null) {
			response.sendRedirect("Login.action");
			return;
		}
		String entYearStr = "";
		String no = "";
		String name = "";
		String classNum = "";
		String isAttendStr = "";
		int entYear = 0;
		StudentDao sDao = new StudentDao();
		
		entYearStr = request.getParameter("ent_year");
		no = request.getParameter("no");
		name = request.getParameter("name");
		classNum = request.getParameter("class_num");
		isAttendStr = request.getParameter("is_attend");
		
		if (entYearStr != null && !entYearStr.equals("")) {
			entYear = Integer.parseInt(entYearStr);
		}
		boolean isAttend = Boolean.parseBoolean(isAttendStr);
		Student student = new Student();
		student.setNo(no);
		student.setName(name);
		student.setEntYear(entYear);
		student.setClassNum(classNum);
		student.setAttend(isAttend);
		student.setSchool(teacher.getSchool());
		sDao.save(student);
		
		request.getRequestDispatcher("/scoremanager/main/student_update_done.jsp").forward(request, response);
	}
}
