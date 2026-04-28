package tool;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//@WebServlet(urlPatterns={"*.action"})// .actionで終わるURLをこのサーブレットで処理
public class FrontController extends HttpServlet {
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException {
		
	}
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException {
		doGet(request, response);
	}
}
