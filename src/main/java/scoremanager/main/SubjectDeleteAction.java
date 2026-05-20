package scoremanager.main;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import bean.School;
import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import tool.Action;


public class SubjectDeleteAction extends Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession session = request.getSession();
		Teacher teacher = (Teacher)session.getAttribute("teacher");
		if (teacher == null) {
			response.sendRedirect("Login.action");
			return;
		}
		School school = teacher.getSchool();
		String cd = request.getParameter("cd");
		
		SubjectDao dao = new SubjectDao();
		Subject subject = dao.get(school.getCd(), cd);
		
		request.setAttribute("subject", subject);
//		String subjectCd = request.getParameter("cd");
//		String subjectName = request.getParameter("name");
//		
//		request.setAttribute("cd",subjectCd);
//		request.setAttribute("name",subjectName);
		
		request.getRequestDispatcher("/scoremanager/main/subject_delete.jsp").forward(request,response);
	}
}
