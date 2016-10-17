package com.courtalon.springweb1_exo1Form.servlets;

import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.courtalon.springweb1_exo1Form.metier.Intervention;
import com.courtalon.springweb1_exo1Form.utils.InterventionDAO;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;


public class IndexServlet extends SpringBaseServlet {
	
	private static Logger log = LogManager.getLogger(IndexServlet.class); 
	private static final long serialVersionUID = 1L;

	

	private InterventionDAO interventionDAO;

	@Override
	public void init() throws ServletException {
		super.init();
		this.interventionDAO = ctx.getBean("interventionDAO", InterventionDAO.class);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("interventionID"));
		interventionDAO.remove(id);
		response.sendRedirect("Index");
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) 
      throws IOException, ServletException {
		System.out.println("query string = " + request.getQueryString());
		boolean tri = false;
		String querystring = request.getQueryString();
		if (querystring != null && querystring.trim().equals("tri=date")) {
			tri = true;
		}
		List<Intervention> interventions = interventionDAO.findAll(tri);
		request.setAttribute("interventions", interventions);
		getServletContext().getRequestDispatcher("/liste.jsp")
						   .forward(request, response);
		
   }

}
