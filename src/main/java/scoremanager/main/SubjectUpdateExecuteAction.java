package scoremanager.main;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import bean.School;
import bean.Subject;
import dao.SubjectDao;
import tool.Action;

public class SubjectUpdateExecuteAction extends Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		School school = (School) request.getSession().getAttribute("school");
		
		String cd = request.getParameter("cd");
		String name = request.getParameter("name");
		
		Subject subject = new Subject();
		subject.setCd(cd);
		subject.setName(name);
		
		SubjectDao dao = new SubjectDao();
		dao.save(school,subject);
		
		request.getRequestDispatcher("subject_update_done.jsp").forward(request, response);
		
	}
}
