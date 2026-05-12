package scoremanager;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import bean.Teacher;
import dao.TeacherDao;
import tool.Action;

public class LoginExecuteAction extends Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		TeacherDao teacherDao = new TeacherDao();
		Teacher teacher = teacherDao.login(id, password);

		if (teacher == null) {
		    // ログイン失敗
		    request.setAttribute("error", "ログインに失敗しました。IDまたはパスワードが正しくありません。");
		    request.getRequestDispatcher("/Login.action").forward(request, response);
		    return;
		}

		// ログイン成功
		request.getSession().setAttribute("teacher", teacher);
		request.getRequestDispatcher("/Menu.action").forward(request, response);
	}
}
