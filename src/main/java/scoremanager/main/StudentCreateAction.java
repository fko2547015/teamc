package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import bean.Teacher;
import dao.ClassNumDao;
import tool.Action;

public class StudentCreateAction extends Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession session = request.getSession();
		Teacher teacher = (Teacher)session.getAttribute("teacher");
		if (teacher == null) {
			response.sendRedirect("Login.action");
			return;
		}
		
		LocalDate todaysDate = LocalDate.now();
		int year = todaysDate.getYear();
		ClassNumDao cNumDao = new ClassNumDao();
		
		// リストを初期化
		List<Integer> entYearSet = new ArrayList<>();
		// 10年前から1年後までリストに追加
		for (int i = year-10; i < year+10; i++) {
			entYearSet.add(i);
		}
		
		// DBからデータ取得
		List<String> list = cNumDao.filter(teacher.getSchool());
	
		request.setAttribute("class_num_set", list);
		request.setAttribute("ent_year_set", entYearSet);
		
		request.getRequestDispatcher("/scoremanager/main/student_create.jsp").forward(request, response);
		
	}
}
