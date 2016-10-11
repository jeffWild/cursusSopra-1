package com.courtalon.jpaWebForm.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.courtalon.jpaWebForm.metier.Categorie;
import com.courtalon.jpaWebForm.utils.CategorieDAO;

/**
 * Servlet implementation class CategorieServlet
 */
public class CategorieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private CategorieDAO categorieDAO;
    
    public CategorieServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
  
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		categorieDAO = (CategorieDAO)getServletContext().getAttribute("categorieDAO");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Categorie> categories = categorieDAO.findAll();
		
		request.setAttribute("categories", categories);
		
		getServletContext().getRequestDispatcher("/categories.jsp")
							.forward(request, response);
	}

	

}
