package scoremanager.main;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import bean.School;
import bean.Subject;
import dao.SubjectDao;
import tool.Action;

public class SubjectListAction extends Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		School school = (School) request.getSession().getAttribute("school");
		
		SubjectDao dao = new SubjectDao();
		List<Subject> list = dao.filter(school);
		
		request.setAttribute("subjects", list);
		request.getRequestDispatcher("subject_list.jsp");
	}
}
