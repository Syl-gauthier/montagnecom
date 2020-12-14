package com.cimpa.montagnecom.controler;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cimpa.montagnecom.dao.Authenticate;

/**
 * Servlet implementation class AdminConrtoler
 */
@WebServlet("/admin")
public class AdminControler extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminControler() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  HttpSession session = request.getSession(false);
    if(session != null &&session.getAttribute("auth") == "true") {
      if(request.getParameter("page") == null) {
        request.getRequestDispatcher("WEB-INF/admin/list.jsp").forward(request, response);
      } else {
        
      }
    } else {
      request.getRequestDispatcher("WEB-INF/admin/auth.jsp").forward(request, response);
    }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("form") != null && request.getParameter("form").equals("auth") ) {
		  Authenticate auth = Authenticate.instance();
		  if(
		      request.getParameter("form") != null && 
		      request.getParameter("form") != null && 
		      auth.isAuth(request.getParameter("username"), request.getParameter("password"))
		  ) {
		    HttpSession session = request.getSession();
		    session.setAttribute("auth", "true");
		    session.setAttribute("username", request.getParameter("username"));
		    session.setAttribute("password", request.getParameter("password"));
		    request.getRequestDispatcher("WEB-INF/admin/list.jsp").forward(request, response);
		  }
		} else {
		  doGet(request, response);
		}
		
	}

}
