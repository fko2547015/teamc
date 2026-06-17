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
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("teacher");

        if (teacher == null) {
            response.sendRedirect("Login.action");
            return;
        }
        
        TestDao tDao = new TestDao();
        List<Test> tests = (List<Test>) session.getAttribute("tests");

        if (tests == null || tests.isEmpty()) {
            response.sendRedirect("TestRegist.action");
            return;
        }

        Map<String, String> errors = new HashMap<>();

        for (Test test : tests) {
            String studentNo = test.getStudent().getNo();
            String pointStr = request.getParameter("point_" + studentNo);

            if (pointStr != null && !pointStr.equals("")) {
                try {
                    int point = Integer.parseInt(pointStr);

                    if (point >= 0 && point <= 100) {
                        test.setPoint(point);
                    } else {
                        // ★ ここが重要（キーを学生番号にする）
                        errors.put(studentNo, "0～100の範囲で入力してください");
                    }

                } catch (NumberFormatException e) {
                    errors.put(studentNo, "数値を入力してください");
                }
            }
        }

        // ★ エラーがある場合
        if (!errors.isEmpty()) {
    		request.setAttribute("tests", tests);
            request.setAttribute("errors", errors);
            request.getRequestDispatcher("/scoremanager/main/test_regist.jsp")
                   .forward(request, response);
            return;
        }

        // ★ 正常時
        tDao.save(tests);
        session.removeAttribute("tests");

        request.getRequestDispatcher("/scoremanager/main/test_regist_done.jsp")
               .forward(request, response);
    }
}
