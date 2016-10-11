package com.courtalon.jpaWebForm.servlets;

import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.courtalon.jpaWebForm.metier.Post;
import com.courtalon.jpaWebForm.utils.PostDAO;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;


public class IndexServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;



	private static Logger log = LogManager.getLogger(IndexServlet.class); 


	
	private PostDAO postDAO;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		postDAO = (PostDAO)getServletContext().getAttribute("postDAO");
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) 
      throws IOException, ServletException {

	  List<Post> posts = postDAO.findAll();
	  
	  log.info("appel de doGet dans IndexServlet");
	  
	  // on prepare les données a afficher
	  request.setAttribute("posts", posts);
	  // on passe le controle a la vue, c.a.d la page jsp
	  getServletContext().getRequestDispatcher("/posts.jsp")
	  					 .forward(request, response);
	  
    /*  response.setContentType("text/html");

      PrintWriter out = response.getWriter();
      
      out.println("<html>");
      out.println("<head>");
      out.println("<title>Bonjour tomcat</title>");
      out.println("</head>");
      out.println("<body>");
      out.println("<h1>Bonjour tomcat</h1>");
      out.println("<ul>");
      for (Post p : posts) {
    	  out.println("<li>" + p.getTitre() + "</li>");
      }
      out.println("</ul>");
      out.println("</body>");
      out.println("</html>");*/
	  
   }

}
