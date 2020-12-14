package com.cimpa.montagnecom.controler;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cimpa.montagnecom.dao.ManageMontagne;

/**
 * Servlet implementation class UserControler
 */
@WebServlet("/user")
public class UserControler extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public UserControler() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("page") == null) {
		  ManageMontagne mm = ManageMontagne.instance();
		  request.setAttribute("listMontagne", mm.selectMontagneList(10, 0));
		  request.setAttribute("listType", mm.selectTypeList());
		  request.setAttribute("listChaine", mm.selectChaineList());
		  request.getRequestDispatcher("WEB-INF/user/index.jsp").forward(request, response);
		} else {
		  
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
