package scoremanager.main;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import bean.Subject;
import dao.SubjectDao;
import tool.Action;

public class SubjectDeleteExecuteAction extends Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		//科目コード所得
		String Cd = request.getParameter("cd");
		
		//DAOから科目情報所得
		SubjectDao dao= new SubjectDao();
		Subject subjet = dao.get(Cd);
		
		request.setAttribute("subject_cd", subjet.getCd());
		request.setAttribute("subject_name", subjet.getName());
		
		request.getRequestDispatcher("subject_delete_done.jsp").forward(request, response);
	}
}
