package scoremanager.main;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import bean.Subject;
import dao.SubjectDao;
import tool.Action;

public class SubjectUpdateAction extends Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		String cd = request.getParameter("cd");
		
		SubjectDao dao = new SubjectDao();
		Subject subject =  dao.get(cd);
		
		request.setAttribute("subject_cd", subject.getCd());
		request.setAttribute("subject_name", subject.getName());
		
		request.getRequestDispatcher("subject_update.jsp").forward(request, response);
		
	}
}
