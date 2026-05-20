package scoremanager.main;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import bean.School;
import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import tool.Action;

public class SubjectDeleteExecuteAction extends Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		HttpSession session = request.getSession();
		Teacher teacher = (Teacher)session.getAttribute("teacher");
		if (teacher == null) {
			response.sendRedirect("Login.action");
			return;
		}
		
		//科目コード所得
		String cd = request.getParameter("cd");
		
		Subject subject = new Subject();
		School school = teacher.getSchool();
		subject.setCd(cd);
		
		SubjectDao dao =new SubjectDao();
		dao.delete(school, subject);
		
		request.getRequestDispatcher("/scoremanager/main/subject_delete_done.jsp").forward(request, response);
	}
}
