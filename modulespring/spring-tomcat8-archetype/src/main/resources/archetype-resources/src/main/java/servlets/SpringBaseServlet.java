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
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.web.context.*;
import org.springframework.web.context.support.WebApplicationContextUtils;


public class SpringBaseServlet extends HttpServlet {
	
	
	@Override
	public void init() throws ServletException {
	    super.init();
	    WebApplicationContext ctx = WebApplicationContextUtils
	            .getWebApplicationContext(getServletContext());
	    AutowireCapableBeanFactory factory = ctx.getAutowireCapableBeanFactory();
	    factory.autowireBean(this);

	}
	


	
}
