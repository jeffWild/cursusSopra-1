package com.courtalon.firstSpringWebForm.servlets;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.courtalon.firstSpringWebForm.metier.Produit;
import com.courtalon.firstSpringWebForm.utils.ProduitDAO;

/**
 * Servlet implementation class ProduitServlet
 */
public class ProduitServlet extends SpringBaseServlet {
	private static final long serialVersionUID = 1L;

	private ProduitDAO produitDAO;
	
    public ProduitServlet() {

    }
    
	@Override
	public void init() throws ServletException {
		super.init();
		produitDAO = ctx.getBean("produitDAO", ProduitDAO.class);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Produit p = null;
		// a t'on recu un parametre ProduitID
		if (request.getParameter("produitID") != null) {
			// oui, c'est une édition
			int pid = Integer.parseInt(request.getParameter("produitID"));
			// je récupere le produit
			p = produitDAO.findByID(pid);
			if (p == null) {
				// pas de produit trouvé, on renvient a la liste
				response.sendRedirect("Index");
				return;
			}
		}
		else {
			// non, c'est un nouveau produit
			p = new Produit(0, "", 0.0, 0.0);
		}
		request.setAttribute("produit", p);
		getServletContext().getRequestDispatcher("/edit.jsp")
						   .forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
