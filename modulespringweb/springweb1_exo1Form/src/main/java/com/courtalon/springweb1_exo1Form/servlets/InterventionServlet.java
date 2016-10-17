package com.courtalon.springweb1_exo1Form.servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.courtalon.springweb1_exo1Form.metier.Intervention;
import com.courtalon.springweb1_exo1Form.utils.InterventionDAO;

/**
 * Servlet implementation class InterventionServlet
 */
public class InterventionServlet extends SpringBaseServlet {
	private static final long serialVersionUID = 1L;

	
	private InterventionDAO interventionDAO;
	private SimpleDateFormat sdf;
	
	
	@Override
	public void init() throws ServletException {
		super.init();
		this.interventionDAO = ctx.getBean("interventionDAO", InterventionDAO.class);
	}
   
    public InterventionServlet() {
    	sdf = new SimpleDateFormat("yyyy-MM-dd");
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if (request.getParameter("interventionID") == null) {
			request.setAttribute("intervention", new Intervention());
		}
		else {
			int id = Integer.parseInt(request.getParameter("interventionID"));
			request.setAttribute("intervention", interventionDAO.findByID(id));
		}
		getServletContext().getRequestDispatcher("/edit.jsp")
							.forward(request, response);
	
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Intervention i = new Intervention(
					Integer.parseInt(request.getParameter("interventionID")),
					request.getParameter("interventionIntervenant"),
					request.getParameter("interventionLieu"),
					request.getParameter("interventionNoMateriel"),
					request.getParameter("interventionDescription"),
					sdf.parse(request.getParameter("interventionDateProgrammee")));
			interventionDAO.save(i);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect("Index");
	}

}
