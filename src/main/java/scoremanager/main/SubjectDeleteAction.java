package scoremanager.main;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import tool.Action;


public class SubjectDeleteAction extends Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String subjectCd = request.getParameter("subject_cd");
		String subjectName = request.getParameter("subject_name");
		
		request.setAttribute("subject_cd",subjectCd);
		request.setAttribute("subject_name",subjectName);
		
		request.getRequestDispatcher("subject_delete.jsp").forward(request,response);
	}
}
