package com.cimpa.montagnecom.controler;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cimpa.montagnecom.dao.Filtre;
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
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
   *      response)
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    ManageMontagne mm = ManageMontagne.instance();
    if (request.getParameter("page") == null) {
      
      request.setAttribute("listMontagne", mm.selectMontagneList(10, 0));
      request.setAttribute("listType", mm.selectTypeList());
      request.setAttribute("listChaine", mm.selectChaineList());
      request.getRequestDispatcher("WEB-INF/user/index.jsp").forward(request, response);
    } else {
      switch (request.getParameter("page")) {
      case "filter":
        Filtre f = new Filtre();
        if (request.getParameter("recherche") != null && !request.getParameter("recherche").equals("")) {
          f.setRecherche(request.getParameter("recherche"));
        }
        if (request.getParameter("chaine") != null && !request.getParameter("chaine").equals("")) {
          f.setIdChaine(Integer.parseInt(request.getParameter("chaine")));
        }
        if (request.getParameter("type") != null && !request.getParameter("type").equals("")) {
          f.setIdType(Integer.parseInt(request.getParameter("type")));
        }
        request.setAttribute("listMontagne", mm.selectMontagneList(10, 0, f));
        request.setAttribute("listType", mm.selectTypeList());
        request.setAttribute("listChaine", mm.selectChaineList());
        request.getRequestDispatcher("WEB-INF/user/index.jsp").forward(request, response);
        return;
      }
    }
  }

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
   *      response)
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // TODO Auto-generated method stub
    doGet(request, response);
  }

}
