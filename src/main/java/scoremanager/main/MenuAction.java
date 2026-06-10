package scoremanager.main;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import bean.Teacher;
import tool.Action;

public class MenuAction extends Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession session = request.getSession();
		Teacher teacher = (Teacher)session.getAttribute("teacher");
		if (teacher == null) {
			response.sendRedirect("Login.action");
			return;
		}
		
		request.getRequestDispatcher("/scoremanager/main/menu.jsp").forward(request, response);
		
	}
}