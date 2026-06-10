package scoremanager.main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import bean.Teacher;
import bean.Test;
import dao.TestDao;
import tool.Action;

public class TestRegistExecuteAction extends Action {
	@SuppressWarnings("unchecked")
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession session = request.getSession();
		Teacher teacher = (Teacher)session.getAttribute("teacher");
		if (teacher == null) {
			response.sendRedirect("Login.action");
			return;
		}

		String pointStr = "0";
		int point;
		TestDao tDao = new TestDao();
		List<Test> tests = null;
		Map<String,String> errors = new HashMap<>();
		
		tests = (List<Test>)session.getAttribute("tests");
		if (tests == null || tests.isEmpty()) {
	        response.sendRedirect("TestRegist.action");
	        return;
	    }
		for (Test test : tests) {
			pointStr = request.getParameter("point_" + test.getStudent().getNo());
			if (pointStr != null && !pointStr.equals("")) {
				point = Integer.parseInt(pointStr);
				if (point >= 0 && point <= 100) {
					test.setPoint(point);
				} else {
					errors.put("f2","0~100の範囲で入力してください");
					request.setAttribute("errors",errors);
				}
			}
		}
		
		tDao.save(tests);
		session.removeAttribute("tests");
		
		request.getRequestDispatcher("/scoremanager/main/test_regist_done.jsp").forward(request, response);
	}
}
