package scoremanager.main;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import bean.School;
import bean.Subject;
import dao.SubjectDao;
import tool.Action;

public class SubjectDeleteExecuteAction extends Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		School school = (School) request.getSession().getAttribute("school"); 
		//科目コード所得
		String cd = request.getParameter("cd");
		
		Subject subject = new Subject();
		subject.setCd(cd);
		
		SubjectDao dao =new SubjectDao();
		dao.delete(school, subject);
		
		request.getRequestDispatcher("subject_delete_done.jsp").forward(request, response);
	}
}
