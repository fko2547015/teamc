package scoremanager.main;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import bean.Subject;
import dao.SubjectDao;
import tool.Action;

public class SubjectCreateExecuteAction extends Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String subjectCd = request.getParameter("subject_cd");
		String subjectName = request.getParameter("subject_name");
		
		Subject subject = new Subject();
		subject.setCd(subjectCd);
		subject.setName(subjectName);
		
		SubjectDao dao = new SubjectDao();
		dao.save(subject);
		
		request.getRequestDispatcher("subject_create_done.jsp").forward(request, response);
	}
}
