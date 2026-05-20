package scoremanager.main;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import bean.School;
import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import tool.Action;

public class SubjectUpdateExecuteAction extends Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession session = request.getSession();
		Teacher teacher = (Teacher)session.getAttribute("teacher");
		if (teacher == null) {
			response.sendRedirect("Login.action");
			return;
		}
		
		String cd = request.getParameter("cd");
		String name = request.getParameter("name");
		
		Subject subject = new Subject();
		School school = teacher.getSchool();
		subject.setCd(cd);
		subject.setName(name);
		
		SubjectDao dao = new SubjectDao();
		dao.save(school,subject);
		
		request.getRequestDispatcher("/scoremanager/main/subject_update_done.jsp").forward(request, response);
		
	}
}
