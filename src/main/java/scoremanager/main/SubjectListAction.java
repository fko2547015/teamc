package scoremanager.main;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import bean.School;
import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import tool.Action;

public class SubjectListAction extends Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession session = request.getSession();
		Teacher teacher = (Teacher)session.getAttribute("teacher");
		if (teacher == null) {
			response.sendRedirect("Login.action");
			return;
		}
		
		SubjectDao dao = new SubjectDao();
		School school = teacher.getSchool();
		List<Subject> list = dao.filter(school);
		
		request.setAttribute("subjects", list);
		request.getRequestDispatcher("/scoremanager/main/subject_list.jsp").forward(request, response);
	}
}
