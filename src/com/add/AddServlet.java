package com.add;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	String no1=(String) req.getParameter("firstTextBox");
	String no2=(String) req.getParameter("secondTextBox");
	System.out.println(no1);
	System.out.println(no2);
	int result=Integer.parseInt(no1)  +Integer.parseInt(no2);
	/*System.out.println(result);
	PrintWriter printWriter=resp.getWriter();
	printWriter.println(result);
	*/
	req.getSession().setAttribute("DisplayResult", result);
	req.getRequestDispatcher("AdditionWebPage.jsp").forward(req, resp);
	
	}
}
