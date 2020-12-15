package com.cimpa.montagnecom.controler;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cimpa.montagnecom.dao.Authenticate;
import com.cimpa.montagnecom.dao.ManageMontagne;
import com.cimpa.montagnecom.model.Chaine;
import com.cimpa.montagnecom.model.Montagne;
import com.cimpa.montagnecom.model.Type;

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
	  request.setCharacterEncoding("UTF-8");
	  response.setCharacterEncoding("UTF-8");
	  ManageMontagne mm = ManageMontagne.instance();
    if(isSessionAuth(request)) {
      if(request.getParameter("page") == null) {
        request.setAttribute("listMontagne", mm.selectMontagneList(10, 0));
        request.getRequestDispatcher("WEB-INF/admin/list.jsp").forward(request, response);
      } else {
        switch(request.getParameter("page")) {
          case "detail":
            System.out.println("going to detail");
            if(request.getParameter("nommontagne") != null) {
              System.out.println("details for " + request.getParameter("nommontagne"));
              request.setAttribute("m", mm.selectMontagne(request.getParameter("nommontagne")));
              request.getRequestDispatcher("WEB-INF/admin/detail.jsp").forward(request, response);
            }
            break;
        }
      }
    } else {
      request.getRequestDispatcher("WEB-INF/admin/auth.jsp").forward(request, response);
    }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  request.setCharacterEncoding("UTF-8");
	  response.setCharacterEncoding("UTF-8");
	  ManageMontagne mm = ManageMontagne.instance();
		if(request.getParameter("form") != null) {
		  switch(request.getParameter("form")) {
		    case "auth":
    		  Authenticate auth = Authenticate.instance();
    		  if(
    		      request.getParameter("username") != null && 
    		      request.getParameter("password") != null && 
    		      auth.isAuth(request.getParameter("username"), request.getParameter("password"))
    		  ) {
    		    HttpSession session = request.getSession();
    		    session.setAttribute("auth", "true");
    		    session.setAttribute("username", request.getParameter("username"));
    		    session.setAttribute("password", request.getParameter("password"));
    		  }
    		  break;
		    case "ajout":
		      if(isSessionAuth(request)) {
		        Montagne m = new Montagne();
		        if (request.getParameter("nom") != null) m.setNom(request.getParameter("nom"));
		        if (request.getParameter("prix") != null) m.setPrix(Double.valueOf(request.getParameter("prix")));
		        if (request.getParameter("altitude") != null) m.setAltitude(Integer.valueOf(request.getParameter("altitude")));
		        if (request.getParameter("image") != null) m.setImage(request.getParameter("image"));
		        if (request.getParameter("description") != null) m.setDescription(request.getParameter("description"));
		        if (request.getParameter("chaine") != null) m.setChaine(new Chaine(0, request.getParameter("chaine")));
		        if (request.getParameter("type") != null) m.setType(new Type(0, request.getParameter("type")));
		        System.out.println(m);
		        mm.addMontagne(m);
		      }
		      break;
		    case "suppr":
		      if(isSessionAuth(request)) {
		        if (request.getParameter("nom") != null) mm.deleteMontagne(request.getParameter("nom"));
		      }
		      break;
		    case "update":
		      if(isSessionAuth(request)) {
		        Montagne m = new Montagne();
            if (request.getParameter("nom") != null) m.setNom(request.getParameter("nom"));
            if (request.getParameter("prix") != null) m.setPrix(Double.valueOf(request.getParameter("prix")));
            if (request.getParameter("altitude") != null) m.setAltitude(Integer.valueOf(request.getParameter("altitude")));
            if (request.getParameter("image") != null) m.setImage(request.getParameter("image"));
            if (request.getParameter("description") != null) m.setDescription(request.getParameter("description"));
            if (request.getParameter("chaine") != null) m.setChaine(new Chaine(0, request.getParameter("chaine")));
            if (request.getParameter("type") != null) m.setType(new Type(0, request.getParameter("type")));
            System.out.println(m);
            mm.updateMontagne(m);
		      }
		  }
		}
		doGet(request, response);
	}
	
	private boolean isSessionAuth(HttpServletRequest request) {
	  HttpSession session = request.getSession(false);
    return session != null && session.getAttribute("auth") == "true";
	}
	
}
