

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class SessionChecker
 */
@WebFilter("/SessionChecker")
public class SessionChecker implements Filter {
	
	private String contextPath;
	
    public SessionChecker() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		 HttpServletRequest req = (HttpServletRequest) request;
		    HttpServletResponse res = (HttpServletResponse) response;  

		    if (req.getSession().getAttribute("userid") == null) { //checks if there's a LOGIN_USER set in session...
		        res.sendRedirect(contextPath + "../WebContent/login/login.html"); //or page where you want to redirect
		    } else {
		      String userType = (String) req.getSession().getAttribute("userid");
		      if (!userType.equals( req.getSession().getAttribute("password") )){
		        res.sendRedirect(contextPath + "../WebContent"); //or page where you want to  
		      }
		    }
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		contextPath = fConfig.getServletContext().getContextPath();
	}

}
