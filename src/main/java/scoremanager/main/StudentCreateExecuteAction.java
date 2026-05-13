package scoremanager.main;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import bean.Student;
import bean.Teacher;
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
		
		if (entYearStr != null) {
			entYear = Integer.parseInt(entYearStr);
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
