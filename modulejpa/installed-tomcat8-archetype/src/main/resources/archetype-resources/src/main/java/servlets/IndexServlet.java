#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.servlets;

import java.util.Date;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;


public class IndexServlet extends HttpServlet {
	
	private static Logger log = LogManager.getLogger(IndexServlet.class); 
	private static final long serialVersionUID = 1L;

	


	public void doGet(HttpServletRequest request, HttpServletResponse response) 
      throws IOException, ServletException {

      response.setContentType("text/html");

      PrintWriter out = response.getWriter();
      
      out.println("<html>");
      out.println("<head>");
      out.println("<title>Bonjour tomcat</title>");
      out.println("</head>");
      out.println("<body>");
      out.println("<h1>Bonjour tomcat</h1>");
      out.println("</body>");
      out.println("</html>");
   }

}
