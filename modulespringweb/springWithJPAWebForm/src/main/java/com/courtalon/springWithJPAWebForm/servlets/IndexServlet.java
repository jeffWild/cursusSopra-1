package com.courtalon.springWithJPAWebForm.servlets;

import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.courtalon.springWithJPAWebForm.metier.Produit;
import com.courtalon.springWithJPAWebForm.repositories.IProduitDAO;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;


public class IndexServlet extends SpringBaseServlet {
	
	private static Logger log = LogManager.getLogger(IndexServlet.class); 
	private static final long serialVersionUID = 1L;

	
	private IProduitDAO produitDAO;

	@Override
	public void init() throws ServletException {
		super.init();
		// je récupere le DAO "decoré" par spring avec les fonctionnalité
		// automatiques pour JPA
		produitDAO = ctx.getBean("produitDAO", IProduitDAO.class);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) 
		      throws IOException, ServletException {
		if (request.getParameter("produitID") != null) {
			int pid = Integer.parseInt(request.getParameter("produitID"));
			// appeler le DAO
			produitDAO.remove(pid);
		}
		response.sendRedirect("/index");
	}
	

	public void doGet(HttpServletRequest request, HttpServletResponse response) 
      throws IOException, ServletException {

		List<Produit> produits = produitDAO.findAll();
		request.setAttribute("produits", produits);
		getServletContext().getRequestDispatcher("/liste.jsp")
							.forward(request, response);
   }

}
