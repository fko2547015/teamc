package scoremanager.main;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import bean.Subject;
import dao.SubjectDao;
import tool.Action;

public class SubjectDeleteExecuteAction extends Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		String subjectCd = request.getParameter("subject_cd");
		
		Subject subject = new Subject();
		
		subject.setCd(subjectCd);
		
		SubjectDao dao= new SubjectDao();
		
		dao.delete(subject);
		
		request.getRequestDispatcher("subject_delete_done.jsp").forward(request, response);
	}
}
