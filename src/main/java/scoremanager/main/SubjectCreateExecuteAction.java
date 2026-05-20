package scoremanager.main;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import bean.School;
import bean.Subject;
import dao.SubjectDao;
import tool.Action;

public class SubjectCreateExecuteAction extends Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		School school = (School) request.getSession().getAttribute("school");
		
		String subjectCd = request.getParameter("cd");
		String subjectName = request.getParameter("name");
		
		Subject subject = new Subject();
		subject.setCd(subjectCd);
		subject.setName(subjectName);
		
		SubjectDao dao = new SubjectDao();
		dao.save(school, subject);
		
		request.getRequestDispatcher("subject_create_done.jsp").forward(request, response);
	}
}
