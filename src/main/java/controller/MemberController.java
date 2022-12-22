package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.MemberDAO;

@WebServlet("/")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MemberController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		doPro(request,response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		doPro(request,response);
	}
	
	protected void doPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	String context = request.getContextPath();
	String command = request.getServletPath();
	String site = null;
	
	MemberDAO member = new MemberDAO();
	
	switch (command) {
	case "/home":
		site ="index.jsp";
		break;
	case "/lookup":
		site = member.select1(request, response);
		break;
	case "/voteins":
		site = member.select2(request, response);
		break;
	case "/rank":
		site = member.rank(request, response);
		break;
	case "/vote":
		site = "vote.jsp";
		break;
	case "/insert":
		int result1 = member.insert(request, response);
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		if(result1 == 1) {
			out.println("<script>");
			out.println("alert('투표 정보가 정상적으로 등록됐습니다.'); location.href='" + context +"';");
			out.println("</script>");
			out.flush();
		}else {
			out.println("<script>");
			out.println("alert('투표 정보가 등록되지 않았습니다.'); location.href='" + context +"';");
			out.println("</script>");
			out.flush();
		}
		break;

	}

	getServletContext().getRequestDispatcher("/"+site).forward(request, response);
	}

}
