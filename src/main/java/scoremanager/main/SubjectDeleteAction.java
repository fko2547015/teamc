package scoremanager.main;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import bean.Teacher;
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
		String subjectCd = request.getParameter("cd");
		String subjectName = request.getParameter("name");
		
		request.setAttribute("cd",subjectCd);
		request.setAttribute("name",subjectName);
		
		request.getRequestDispatcher("/scoremanager/main/subject_delete.jsp").forward(request,response);
	}
}
