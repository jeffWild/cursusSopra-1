package com.courtalon.firstSpringWebForm.servlets;

import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.courtalon.firstSpringWebForm.beans.Message;
import com.courtalon.firstSpringWebForm.metier.Produit;
import com.courtalon.firstSpringWebForm.utils.ProduitDAO;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class IndexServlet extends SpringBaseServlet {
	
	private static Logger log = LogManager.getLogger(IndexServlet.class); 
	private static final long serialVersionUID = 1L;

	private ProduitDAO produitDAO;

	@Override
	public void init() throws ServletException {
		super.init();
		this.produitDAO = ctx.getBean("produitDAO", ProduitDAO.class);
	}


	public void doGet(HttpServletRequest request, HttpServletResponse response) 
      throws IOException, ServletException {

	  List<Produit> produits = produitDAO.findAll();
	  request.setAttribute("produits", produits);
	  getServletContext().getRequestDispatcher("/produits.jsp")
	  					.forward(request, response);
	  
		
		
   /*   response.setContentType("text/html");

      PrintWriter out = response.getWriter();
      
      // recuperation du bean msg1 de l'applicationContext
      Message msg = ctx.getBean("msg1", Message.class);
      
      out.println("<html>");
      out.println("<head>");
      out.println("<title>Bonjour tomcat</title>");
      out.println("</head>");
      out.println("<body>");
      out.println("<h1>" + msg.getCorps() + "</h1>");
      out.println("</body>");
      out.println("</html>");*/
   }

}
