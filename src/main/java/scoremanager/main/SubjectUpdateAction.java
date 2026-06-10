package scoremanager.main;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import bean.School;
import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import tool.Action;

public class SubjectUpdateAction extends Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession session = request.getSession();
		Teacher teacher = (Teacher)session.getAttribute("teacher");
		if (teacher == null) {
			response.sendRedirect("Login.action");
			return;
		}
		String cd = request.getParameter("cd");
		
		SubjectDao dao = new SubjectDao();
		School school = teacher.getSchool();
		Subject subject =  dao.get(school.getCd(), cd);
		
		request.setAttribute("subject", subject);
		
		request.getRequestDispatcher("/scoremanager/main/subject_update.jsp").forward(request, response);
		
	}
}
