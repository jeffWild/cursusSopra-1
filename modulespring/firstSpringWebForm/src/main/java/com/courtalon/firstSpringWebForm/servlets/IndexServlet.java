package com.courtalon.firstSpringWebForm.servlets;

import java.util.Date;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.courtalon.firstSpringWebForm.beans.Message;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class IndexServlet extends SpringBaseServlet {
	
	private static Logger log = LogManager.getLogger(IndexServlet.class); 
	private static final long serialVersionUID = 1L;



	public void doGet(HttpServletRequest request, HttpServletResponse response) 
      throws IOException, ServletException {

      response.setContentType("text/html");

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
      out.println("</html>");
   }

}
