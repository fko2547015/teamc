package scoremanager.main;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import bean.School;
import bean.Subject;
import dao.SubjectDao;
import tool.Action;

public class SubjectUpdateAction extends Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		School school = (School) request.getSession().getAttribute("school");
		
		String cd = request.getParameter("cd");
		
		SubjectDao dao = new SubjectDao();
		Subject subject =  dao.get(school.getCd(), cd);
		
		request.setAttribute("cd", subject.getCd());
		request.setAttribute("name", subject.getName());
		
		request.getRequestDispatcher("subject_update.jsp").forward(request, response);
		
	}
}
