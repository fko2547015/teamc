package scoremanager.main;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import tool.Action;


public class SubjectDeleteAction extends Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String subjectCd = request.getParameter("cd");
		String subjectName = request.getParameter("name");
		
		request.setAttribute("cd",subjectCd);
		request.setAttribute("name",subjectName);
		
		request.getRequestDispatcher("subject_delete.jsp").forward(request,response);
	}
}
