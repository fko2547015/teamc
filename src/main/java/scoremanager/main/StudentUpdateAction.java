package scoremanager.main;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import bean.Student;
import bean.Teacher;
import dao.ClassNumDao;
import dao.StudentDao;
import tool.Action;

public class StudentUpdateAction extends Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession session = request.getSession();
		Teacher teacher = (Teacher)session.getAttribute("teacher");
		if (teacher == null) {
			response.sendRedirect("Login.action");
			return;
		}
		String no = request.getParameter("no");
		StudentDao sDao = new StudentDao();
		ClassNumDao cNumDao = new ClassNumDao();
		
		// DBからデータ取得
		Student student = sDao.get(no);
		List<String> list = cNumDao.filter(teacher.getSchool());
		
		String name = student.getName();
		String classNum = student.getClassNum();
		int entYear = student.getEntYear();
		boolean isAttend = student.isAttend();
		
		request.setAttribute("no", no);
	    request.setAttribute("name", name);
	    request.setAttribute("class_num", classNum);
	    request.setAttribute("ent_year", entYear);
	    request.setAttribute("is_attend", isAttend);
		request.setAttribute("class_num_set", list);
		
		request.getRequestDispatcher("/scoremanager/main/student_update.jsp").forward(request, response);
		
	}
}
